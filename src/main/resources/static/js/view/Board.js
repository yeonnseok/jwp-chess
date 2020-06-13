import { boardSquaresTemplate } from "/js/utils/templates.js";
import { EVENT_TYPE } from "/js/utils/constants.js";

function Board() {
    const boardId = window.location.hash.substring(1)
    const $boardInner = document.querySelector('#board-inner')
    let $squares
    let moveInfo = []

    const onMovePiece = async event => {
        const $target = event.target
        const $square = $target.closest('DIV')
        if ($target && $square.classList.contains('square')) {
            moveInfo.push($square.id)
        }
        if (moveInfo.length === 2) {
            try {
                await fetch(`/boards/${boardId}/move?from=${moveInfo[0]}&to=${moveInfo[1]}`, {
                    method : 'PUT',
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).catch(err => console.log(err))
                getBoardSquares()
            } catch (e) {
                alert(e, "오류!")
            }
            moveInfo = []
        }
    }

    const getBoardSquares = async () => {
        const id = window.location.hash.substring(1)
        const template = await fetch(`/boards/${id}`, {
            method : 'GET',
            headers : {
                'ContentType' : 'application/json'
            }
        }).then(data => data.json())
            .then(board => boardSquaresTemplate(board.squares));
        $boardInner.innerHTML = template

        initEventListener()
    }

    const initEventListener = () => {
        $squares = document.querySelector('#board-squares')
        $squares.addEventListener(EVENT_TYPE.CLICK, onMovePiece)
    }

    this.init = () => {
        getBoardSquares()
    }
}

const board = new Board()
board.init()