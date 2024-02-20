# -*- coding: utf-8 -*-
from flask import Flask, render_template, redirect , url_for, request
from flask_cors import CORS
import cv2
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout
from tensorflow.keras.applications.vgg16 import VGG16, preprocess_input, decode_predictions
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import os
import random
import requests

#app = Flask(__name__)
app = Flask(__name__, static_folder='static')
CORS(app)

# OpenWeatherMap API 키와 도시 정보 설정
api_key = '484e918d6e08b9c8062ba9338f2f3a56'

# IPStack API 엔드포인트
ipstack_api_url = 'http://api.ipstack.com/check'

# Geolocation API 엔드포인트
geolocation_api_url = 'https://ipinfo.io/json'

# OpenWeatherMap API 엔드포인트
weather_api_url = 'http://api.openweathermap.org/data/2.5/weather'

# 미리 학습된 VGG16 모델 로드
model_vgg = VGG16(weights='imagenet')

# 데이터셋을 저장할 폴더 경로
dataset_folder = "mood_1"

#출력 이미지 폴더
dataset_folder_1 = "static/mood_result"

# 클래스 레이블 정의
class_labels = sorted(os.listdir(dataset_folder))

# 데이터셋 불러오기 및 전처리
datagen = ImageDataGenerator(rescale=1./255, validation_split=0.2)

# 이미지 저장을 위한 변수
image_number = 0

# 데이터셋을 훈련 및 검증용으로 나누기
train_generator = datagen.flow_from_directory(
    dataset_folder,
    target_size=(100, 100),
    batch_size=100,
    class_mode='categorical',
    subset='training'
)
val_generator = datagen.flow_from_directory(
    dataset_folder,
    target_size=(100, 100),
    batch_size=100,
    class_mode='categorical',
    subset='validation'
)

# CNN 모델 정의
model_cnn = Sequential([
    Conv2D(100, (3, 3), activation='relu', input_shape=(100, 100, 3)),
    MaxPooling2D((2, 2)),
    Flatten(),
    Dense(32, activation='relu',kernel_initializer='he_normal'),
    Dropout(0.2),
    Dense(len(class_labels), activation='softmax')
])

"""
 Affine 계층이라는 용어는 일반적으로 Fully Connected Layer를 의미합니다.
 즉, Flatten() 다음에 Dense 레이어가 이에 해당합니다.
"""
"""
Relu 함수일때 가중치 초깃값은 he권장"""

#컴파일
model_cnn.compile(optimizer='adam',
                  loss='categorical_crossentropy',
                  metrics=['accuracy'])

# 모델 학습
history_cnn = model_cnn.fit(train_generator, epochs=15, validation_data=val_generator)

#predict_thing 함수
def predict_thing(img_array, model):
    predictions = model.predict(img_array)
    decoded_predictions = decode_predictions(predictions, top=7)[0]

    # 상위 7개 예측 결과의 레이블만 리스트에 담아 반환
    top7_labels = [label for _, label, _ in decoded_predictions]
    return top7_labels

# 랜덤이미지
def get_random_images_by_class(class_label, dataset_folder_1, num_images=3):
    class_folder = os.path.join(dataset_folder_1, class_label)

    # 이미지 파일 경로 생성
    all_images = [os.path.join(class_folder, img) for img in os.listdir(class_folder)]

    # 랜덤하게 이미지 선택
    selected_images = random.sample(all_images, min(num_images, len(all_images)))

    # 경로 구분자를 '/'로 변경
    selected_images = [image.replace('\\', '/') for image in selected_images]
    return selected_images

# 온도를 섭씨로 변환하는 함수
def kelvin_to_celsius(kelvin):
    return kelvin - 273.15

# 날씨 상태에 따른 아이콘 반환 함수
def get_weather_icon(icon_code):
    return f'http://openweathermap.org/img/w/{icon_code}.png'
def get_user_location():
    response = requests.get(geolocation_api_url)
    location_data = response.json()
    return location_data.get('loc')

#지역이름으로 벼경
def get_location_name(lat, lon, api_key):
    opencage_api_url = f'https://api.opencagedata.com/geocode/v1/json?q={lat}+{lon}&key={api_key}'
    response = requests.get(opencage_api_url)
    data = response.json()
    # Extract the location name from the response
    if 'results' in data and len(data['results']) > 0:
        # 'formatted'에서 지역 이름을 추출
        location_name = data['results'][0]['formatted']
        return location_name
    else:
        return None

#시작
@app.route('/')
def hello_world():
    return render_template('hello.html')

#로그인
@app.route('/login')
def login():
    return redirect('http://192.168.0.8:8088/finalproject/fpLoginForm')
    #return redirect('http://localhost:8088/finalproject/fpLoginForm')

#회원정보 수정
@app.route('/fpUpdateForm')
def fpUpdateForm():
    #return redirect('http://localhost:8088/finalproject/fpUpdateForm')
    return redirect('http://192.168.0.8:8088/finalproject/fpUpdateForm')

#회원정보 삭제
@app.route('/memDelete')
def memDelete():
    #return redirect('http://localhost:8088/finalproject/fpUpdateForm')
    return redirect('http://192.168.0.8:8088/finalproject/fpUpdateForm')

#회원가입
@app.route('/member')
def member():
    return redirect('http://192.168.0.8:8088/finalproject/fpInsertForm')
    #return redirect('http://localhost:8088/finalproject/fpInsertForm')

#로그인 성공페이지
@app.route('/success/<mid>',methods=['GET'])
def hello_success(mid):
    user_location = get_user_location()
      # OpenWeatherMap API 호출을 위한 매개변수 설정
    params = {
        'lat': user_location.split(',')[0],
        'lon': user_location.split(',')[1],
        'appid': api_key
    }
    loc_lat = params['lat']
    loc_lon = params['lon']
    loc_key = 'c34dcee4a6d44786b51387c18e716230'
    location_name = get_location_name(loc_lat,loc_lon,loc_key)
    # OpenWeatherMap API에서 날씨 정보 가져오기
    response = requests.get(weather_api_url, params=params)
    weather_data = response.json()
    # 필요한 정보 추출 및 온도 변환
    temperature_kelvin = weather_data['main']['temp']
    temperature_celsius = temperature_kelvin - 273.15
    description = weather_data['weather'][0]['description']
    icon_code = weather_data['weather'][0]['icon']
    # 템플릿으로 전달할 데이터 설정
    template_data = {
        'temperature': round(temperature_celsius, 2),  # 소수점 두 자리까지 표시
        'description': description,
        'icon': get_weather_icon(icon_code),
        'location' : location_name
    }
    return render_template('hello_success.html', mid=mid, **template_data)

#로그아웃
@app.route('/logout')
def logout():
    return render_template('hello.html')

#가구
#@app.route('/funiture')
#def funiture():
#     return redirect('http://172.30.1.90:8088/finalproject/funiture.h')
@app.route('/funiture',methods=['GET'])
def funiture():
    mid = request.args.get('mid')
    return redirect(f'http://192.168.0.2:8088/finalproject/funiture.h?mid={mid}')

#노드서버로
@app.route('/detail/<predicted_class_cnn>/<mid>', methods=['GET'])
def detail(predicted_class_cnn,mid):

    # 노드 서버의 URL 설정
    node_server_url = 'http://localhost:3000'  # 노드 서버의 주소와 포트로 변경

    # 데이터를 전송할 URL 조합
    url = f'{node_server_url}/receive_data'

    # 전송할 데이터를 JSON 형식으로 만즘
    data = {'predicted_class_cnn': predicted_class_cnn, 'mid':mid}

    # 플라스크 서버에서 노드 서버로 POST 요청
    response = requests.post(url, json=data)

    # 노드 서버로부터 받은 응답을 반환
    return response.text

#인테리어 검사
@app.route('/interior/<mid>', methods=['GET'])
def index(mid):
        # 초기화
    image_number = 0

    # 카메라로 실시간 사진 촬영 및 분위기 및 물체 예측
    cap = cv2.VideoCapture(0)
    if cap.isOpened():
        while True:
            ret, frame = cap.read()
            if ret:
                cv2.imshow('camera', frame)
                # 키보드 ' '를 눌러서 이미지 저장
                if cv2.waitKey(1) & 0xFF == ord(' '):
                    image_number += 1
                    # 이미지 저장 시 중복된 번호가 있으면 1을 더한다.
                    while os.path.exists(f"static/testcolor/captured_image_{image_number}.jpg"):
                        image_number += 1
                    image_path = f"static/testcolor/captured_image_{image_number}.jpg"
                    cv2.imwrite(image_path, frame)
                    print(f"!!!!! Image saved as {image_path} !!!!!")
                    # 저장된 이미지를 읽어와 분석
                    test_image = cv2.imread(image_path)
                    test_image_cnn = cv2.resize(test_image, (100, 100))
                    test_image_cnn = np.expand_dims(test_image_cnn, axis=0)
                    test_image_cnn = test_image_cnn / 255.0
                    # 분위기 예측
                    prediction_cnn = model_cnn.predict(test_image_cnn)
                    predicted_class_cnn = class_labels[np.argmax(prediction_cnn)]
                    mood_result = f"Predicted Mood: {predicted_class_cnn}"
                    # 물체 예측
                    img_array_vgg = cv2.resize(test_image, (224, 224))
                    img_array_vgg = np.expand_dims(img_array_vgg, axis=0)
                    img_array_vgg = preprocess_input(img_array_vgg)
                    predicted_objects = predict_thing(img_array_vgg, model_vgg)
                    # 결과 출력
                    print(mood_result)
                    print(predicted_objects)
                    #인식이미지
                    image_path_origin = url_for('static', filename=f"testcolor/captured_image_{image_number}.jpg")
                    # 랜덤하게 이미지 가져오기
                    random_images = get_random_images_by_class(predicted_class_cnn, dataset_folder_1, num_images=3)
                    # 이미지 URL 생성
                    image_urls = [url_for('static', filename=image[7:]) for image in random_images]
                    return render_template('result.html'
                                                        ,predicted_class_cnn = predicted_class_cnn
                                                        ,predicted_objects=predicted_objects
                                                        ,selected_images=image_urls
                                                        ,image_path_origin=image_path_origin
                                                        ,mid=mid)
            else:
                print('no frame!')
                break
    else:
        print('no camera!')
    cap.release()
    cv2.destroyAllWindows()
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5011, debug=False)