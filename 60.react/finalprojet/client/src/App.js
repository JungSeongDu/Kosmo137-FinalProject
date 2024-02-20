import React, { useState, useEffect } from 'react';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import ReactHtmlParser from 'react-html-parser';
import Axios from 'axios';
import './App.css';

function App() {
  const [interiorContent, setInteriorContent] = useState({
    title: '',
    content: ''
  });
  const [viewContent, setViewContent] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);
  const [preview, setPreview] = useState('');
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    Axios.get('http://localhost:8000/api/get').then((response) => {
      if (Array.isArray(response.data)) {
        setViewContent(response.data);
      } else {
        setViewContent([]);
      }
    });
  }, []);

  const submitReview = () => {
    const formData = new FormData();
    formData.append('title', interiorContent.title);
    formData.append('content', interiorContent.content);
    if (selectedImage) {
      formData.append('image', selectedImage);
    }

    const url = editingId ? `http://localhost:8000/api/updateReview/${editingId}` : 'http://localhost:8000/api/submitReview';
    Axios.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }).then(() => {
      alert('등록 완료!');
      setEditingId(null); // Reset editing state
      fetchReviews(); // Refresh the list of reviews
    }).catch(error => {
      console.error('등록 실패:', error);
    });
  };

  const handleImageChange = (e) => {
    if (e.target.files && e.target.files[0]) {
      setSelectedImage(e.target.files[0]);
      const reader = new FileReader();
      reader.onload = (e) => setPreview(e.target.result);
      reader.readAsDataURL(e.target.files[0]);
    }
  };

  const getValue = e => {
    const { name, value } = e.target;
    setInteriorContent({
      ...interiorContent,
      [name]: value
    });
  };

  const startEdit = (review) => {
    setInteriorContent({
      title: review.title,
      content: review.content
    });
    setEditingId(review.id); // Set ID of the review being edited
    // Image handling for editing not shown here
  };

  const deleteReview = (id) => {
    Axios.delete(`http://localhost:8000/api/deleteReview/${id}`).then(() => {
      alert('삭제 완료!');
      fetchReviews(); // Refresh the list of reviews
    }).catch(error => {
      console.error('삭제 실패:', error);
    });
  };

  const fetchReviews = () => {
    Axios.get('http://localhost:8000/api/get').then((response) => {
      if (Array.isArray(response.data)) {
        setViewContent(response.data);
      } else {
        setViewContent([]);
      }
    });
  };

  return (
    <div className="App">
      <h1>Interior Review</h1>
      <div className='interior-container'>
      {viewContent.map((element, index) => (
  <div key={index} style={{ border: '1px solid #333', marginBottom: '20px' }}>
    <h2>{element.title}</h2>
    <div className="button-group">
      <button className="edit-button" onClick={() => startEdit(element)}>수정</button>
      <button className="delete-button" onClick={() => deleteReview(element.id)}>삭제</button>
    </div>
    <div>{ReactHtmlParser(element.content)}</div>
    {element.image_url && <img src={element.image_url} alt="Uploaded" style={{ maxWidth: "100%", maxHeight: "400px" }} />}
  </div>
))}
      </div>
      <div className='form-wrapper'>
        <input className="title-input" type='text' placeholder='제목' value={interiorContent.title} onChange={getValue} name='title' />
        <CKEditor
          editor={ClassicEditor}
          data={interiorContent.content}
          onChange={(event, editor) => {
            const data = editor.getData();
            setInteriorContent({
              ...interiorContent,
              content: data
            });
          }}
        />
        <input type="file" accept="image/*" onChange={handleImageChange} />
        {preview && (
          <div>
            <h2>미리보기</h2>
            <img src={preview} alt="Preview" style={{ maxWidth: "100%", maxHeight: "400px" }} />
          </div>
        )}
        <button className="submit-button" onClick={submitReview}>입력</button>
      </div>
    </div>
  );
}

export default App;


