import { listBoardTemplates } from '/js/utils/templates.js'
import { EVENT_TYPE } from "/js/utils/constants.js";

function Index() {

    const $boardList = document.querySelector('#board-list')
    const $newGameBtn = document.querySelector('#create-btn')
    const $deleteGameBtn = document.querySelector('#delete-btn')

    const onCreateBoard = async event => {
        try {
            await fetch("/boards", {
                method : 'POST',
                headers : {
                    'Content-Type': 'application/json'
                }
            }).catch(err => console.log(err));
        } catch (e) {
            alert("오류!")
        }
        window.location.reload();
    }

    const onDeleteBoard = event => {
        event.preventDefault();
        // TODO: 삭제 버튼을 누르면 보드가 삭제된다.
    }

    const initBoardList = async () => {
        try {
            const template = await fetch("/boards", {
                method : 'GET',
                headers : {
                    'Content-Type': 'application/json'
                }
            }).then(data => data.json())
                .then(boards => boards.map(board => listBoardTemplates(board)).join(""));
            $boardList.innerHTML = template;
        } catch (e) {
            alert(e, '오류!');
        }
    }

    const initEventListener = () => {
        $newGameBtn.addEventListener(EVENT_TYPE.CLICK, onCreateBoard);
        $deleteGameBtn.addEventListener(EVENT_TYPE.CLICK, onDeleteBoard);
    }

    this.init = () => {
        initBoardList();
        initEventListener();
    }

}

const index = new Index();
index.init();