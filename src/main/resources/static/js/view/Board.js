import { boardSquaresTemplate } from "/js/utils/templates.js";

function Board() {

    const $boardInner = document.querySelector('#board-inner')

    const initBoard = async () => {
        const id = window.location.hash.substring(1)
        const template = await fetch(`/boards/${id}`, {
            method : 'GET',
            headers : {
                'ContentType' : 'application/json'
            }
        }).then(data => data.json())
            .then(board => boardSquaresTemplate(board.squares));
        $boardInner.innerHTML = template
    }

    const initEventListener = () => {

    }

    this.init = () => {
        initEventListener()
        initBoard()
    }
}

const board = new Board()
board.init()