public class ReachableFieldsOnChessboard {
    public static void main(String[] args) throws Chessboard.Chesspiece.NotValidFieldException {
        Chessboard chessBoard = new Chessboard();

        Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
        pieces[0] = chessBoard.new Pawn ('w', 'P');
        pieces[1] = chessBoard.new Rook ('b', 'R');
        pieces[2] = chessBoard.new Queen ('w', 'Q');
        pieces[3] = chessBoard.new Bishop ('w', 'B');
        pieces[4] = chessBoard.new King ('b', 'K');
        pieces[5] = chessBoard.new Knight ('w', 'N');

        // Show all the pieces and their markings
        for (Chessboard.Chesspiece piece : pieces) {
            piece.moveTo('d', (byte) (4));
            piece.markReachableFields();
            System.out.println(chessBoard + "\n");
            piece.unmarkReachableFields();
            System.out.println(chessBoard + "\n");
            piece.moveOut();
        }
    }
}