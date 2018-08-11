package com.challenge.application.controller.exceptionhandler;

import com.challenge.application.game.exception.GameException;
import com.challenge.application.game.model.IPlayer;
import com.challenge.server.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameExceptionHandler implements ExceptionHandler<GameException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameExceptionHandler.class);
    private SocketChannel socketChannel;

    public GameExceptionHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    /**
     * Handle GameException by sending error message to player socket.
     *
     * @param ex the exception to handle.
     * @param currentPlayer the authorized current player
     */
    @Override
    public void handle(GameException ex, IPlayer currentPlayer) {
        LOGGER.debug("GameException for player {}. Error message: {}.", currentPlayer, ex.getMessage());
        String errMessage = buildErrorMessage(ex, currentPlayer);
        socketChannel.send(errMessage);
    }

    private String buildErrorMessage(GameException ex, IPlayer currentPlayer) {
        return "ERROR: " +
//                player +
//                ": " +
                ex.getMessage();
    }
}
