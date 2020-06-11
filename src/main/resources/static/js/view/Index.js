import { listBoardTemplates } from '/js/utils/templates.js'
import { EVENT_TYPE } from "/js/utils/constants.js";

function Index() {

    const $boardList = document.querySelector('#board-list')
    const $createBtn = document.querySelector('#create-btn')

    const onCreateBoard = async event => {
        event.preventDefault();
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
        initBoardList()
    }

    const onDeleteBoard = async event => {
        event.preventDefault();
        const $target = event.target
        if ($target.id !== 'delete-btn') {
            return
        }
        try {
            const id = event.target.closest('DIV').dataset.boardId
            await fetch(`/boards/${id}`, {
                method : 'DELETE',
                headers : {
                    'Content-Type' : 'application/json'
                }
            })
        } catch (e) {
            alert(e, "오류!")
        }
        initBoardList()
    }

    const initBoardList = async () => {
        try {
            const template = await fetch("/boards", {
                method : 'GET',
                headers : {
                    'Content-Type': 'application/json'
                }
            }).then(data => data.json())
                .then(boards => boards.map(board => listBoardTemplates(board)).join(""))
            $boardList.innerHTML = template
        } catch (e) {
            alert(e, '오류!')
        }
    }

    const initEventListener = () => {
        $createBtn.addEventListener(EVENT_TYPE.CLICK, onCreateBoard);
        $boardList.addEventListener(EVENT_TYPE.CLICK, onDeleteBoard);
    }

    this.init = () => {
        initBoardList();
        initEventListener();
    }

}

const index = new Index();
index.init();