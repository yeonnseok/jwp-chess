export const boardSquaresTemplate = squares =>
    `<div class="squares">
        <div class="row">
            <div class="square white-square"><img src="/images/${squares.a8}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.b8}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.c8}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.d8}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.e8}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.f8}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.g8}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.h8}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square black-square"><img src="/images/${squares.a7}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.b7}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.c7}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.d7}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.e7}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.f7}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.g7}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.h7}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square white-square"><img src="/images/${squares.a6}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.b6}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.c6}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.d6}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.e6}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.f6}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.g6}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.h6}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square black-square"><img src="/images/${squares.a5}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.b5}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.c5}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.d5}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.e5}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.f5}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.g5}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.h5}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square white-square"><img src="/images/${squares.a4}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.b4}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.c4}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.d4}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.e4}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.f4}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.g4}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.h4}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square black-square"><img src="/images/${squares.a3}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.b3}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.c3}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.d3}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.e3}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.f3}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.g3}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.h3}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square white-square"><img src="/images/${squares.a2}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.b2}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.c2}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.d2}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.e2}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.f2}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.g2}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.h2}.png" alt=""/></div>
        </div>
        <div class="row">
            <div class="square black-square"><img src="/images/${squares.a1}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.b1}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.c1}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.d1}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.e1}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.f1}.png" alt=""/></div>
            <div class="square black-square"><img src="/images/${squares.g1}.png" alt=""/></div>
            <div class="square white-square"><img src="/images/${squares.h1}.png" alt=""/></div>
        </div>
    </div>`

export const listBoardTemplate = data =>
    `<div class="board-item" data-board-id="${data.id}">
        Board Id :  ${data.id}
        <button class="mini-control-btn" id="start-btn">시작하기</button>
        <button class="mini-control-btn" id="delete-btn">삭제하기</button>
    </div>`
