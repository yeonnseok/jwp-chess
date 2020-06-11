export const listBoardTemplates = data =>
    `<div class="board-item">
        Board Id :  ${data.id}
        <button class="mini-control-btn" id="start-btn">시작하기</button>
        <button class="mini-control-btn" id="delete-btn">삭제하기</button>
    </div>`