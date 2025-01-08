document.getElementById('updateUser')
    .addEventListener('submit', async function (event) {
        event.preventDefault();

        const currentPassword = document.getElementById('currentPassword').value || null;
        const newPassword = document.getElementById('newPassword').value || null;
        const newPasswordCheck = document.getElementById('newPasswordCheck').value || null;
        const nickname = document.getElementById('nickname').value || null;
        const phone = document.getElementById('phone').value || null;

        const data = {
            currentPassword: currentPassword,
            newPassword: newPassword,
            newPasswordCheck: newPasswordCheck,
            nickname: nickname,
            phone: phone
        };

        try {
            const response = await fetch('/user/update', {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                const result = await response.json();
                console.log('서버 응답:', result);
                alert('수정이 완료되었습니다.');
            } else {
                const errorText = await response.text();
                console.error('서버 에러:', errorText);
                alert('서버 에러 발생');
            }
        } catch (error) {
            console.error('요청 실패:', error);
            alert('서버 요청 중 문제가 발생했습니다.');
        }
    });
