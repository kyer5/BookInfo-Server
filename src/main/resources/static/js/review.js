document.getElementById('review')
    .addEventListener('submit', async function (event) {
        event.preventDefault();

        const isbn = document.querySelector('.review-regist-btn').getAttribute('data-isbn-value');
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        const data = {
            isbn: isbn,
            title: title,
            content: content
        };

        const response = await fetch('/review/create', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            console.log('서버 응답:', result);
            alert('리뷰가 등록되었습니다.');
            window.location.href = `/book/detail?isbn=${isbn}`;
        } else {
            const errorText = await response.text();
            console.error('서버 에러:', errorText);
            alert('서버 에러 발생');
        }

    });
