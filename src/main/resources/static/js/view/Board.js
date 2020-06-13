import { boardSquaresTemplate } from "/js/utils/templates.js";
import { EVENT_TYPE } from "/js/utils/constants.js";

function Board() {
    const id = window.location.hash.substring(1)
    const $entire = document.querySelector('body');
    const $boardInner = document.querySelector('#board-inner')
    const $turn = document.querySelector('#turn-presenter')
    const $exitBtn = document.querySelector('#exit-button')
    const $scoreBtn = document.querySelector('#score-button')
    const $scorePresenter = document.querySelector('#score-presenter')
    const $whiteScore = document.querySelector('#white-score')
    const $blackScore = document.querySelector('#black-score')

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
                const state = await fetch(`/boards/${id}/move?from=${moveInfo[0]}&to=${moveInfo[1]}`, {
                    method : 'PUT',
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then(data => data.json())
                    .catch(err => console.log(err))
                if (state.finished) {
                    confirm(state.movedTurn + "승!, 게임이 끝났습니다!")
                    await fetch(`/boards/${id}/`, {
                        method : 'DELETE'
                    })
                    window.location.href = '/'
                } else {
                    getBoardSquares()
                }
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
            .then(board => {
                $turn.innerText = board.turn + "차례 입니다."
                return boardSquaresTemplate(board.squares)
            });
        $boardInner.innerHTML = template
        addBoardEventListener()
    }

    const addBoardEventListener = () => {
        $squares = document.querySelector('#board-squares')
        $squares.addEventListener(EVENT_TYPE.CLICK, onMovePiece)
    }

    const onShowScore = async event => {
        try {
            const score = await fetch(`/boards/${id}/score`, {
                method : 'GET',
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then(data => data.json())
                .catch(err => console.log(err))
            $whiteScore.innerText = 'WHITE : ' + score.whiteScore
            $blackScore.innerText = 'BLACK : ' + score.blackScore
            $scorePresenter.style.visibility = 'visible'
        } catch(e) {
            alert(e, "오류!")
        }
    }

    const turnOffScore = event => {
        const $target = event.target;
        if ($target && $target !== $scorePresenter) {
            $scorePresenter.style.visibility = 'hidden'
        }
    }

    const onExitGame = event => {
        event.preventDefault()
        window.location.href = '/'
    }

    const initEventListener = () => {
        $exitBtn.addEventListener(EVENT_TYPE.CLICK, onExitGame)
        $scoreBtn.addEventListener(EVENT_TYPE.CLICK, onShowScore)
        $entire.addEventListener(EVENT_TYPE.CLICK, turnOffScore)
    }

    this.init = () => {
        initEventListener()
        getBoardSquares()
    }
}

const board = new Board()
board.init()