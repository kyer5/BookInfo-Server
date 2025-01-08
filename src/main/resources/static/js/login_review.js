document.addEventListener('DOMContentLoaded', function() {
    const reviewForm = document.getElementById('review');

    reviewForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const confirmLogin = confirm('리뷰 작성은 회원만 가능합니다. 로그인 페이지로 이동하시겠습니까?');

        if (confirmLogin) {
            window.location.href = '/user/login';
            return;
        }

        return;

        const formData = {
            title: document.getElementById('title').value,
            content: document.getElementById('content').value,
            isbn: e.target.querySelector('.review-regist-btn').getAttribute('data-isbn-value')
        };

        fetch('/reviews', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('리뷰 등록에 실패했습니다.');
                }
                return response.json();
            })
            .then(data => {
                alert('리뷰가 성공적으로 등록되었습니다.');
                location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    });
});