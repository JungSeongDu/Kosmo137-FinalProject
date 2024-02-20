const axios = require('axios');

// Flask 애플리케이션의 URL
const flaskUrl = 'http://localhost:5011';

async function getPredictedClass() {
    try {
        const response = await axios.get(`${flaskUrl}/get_predicted_class`);
        const predictedClass = response.data.predicted_class;
        // 이후에는 예측된 분위기 값(predictedClass)을 사용하면 됩니다.
        console.log(predictedClass);
    } catch (error) {
        console.error('Error getting predicted class:', error.message);
    }
}

// getPredictedClass 함수 호출
getPredictedClass();