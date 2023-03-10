package jogadores;

import java.util.Random;

public class AnatolyKarpov extends Jogador{

    public AnatolyKarpov(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {


        if(CheckRowVictoryCondition(tabuleiro)!=null){
            return CheckRowVictoryCondition(tabuleiro); //checando condicao de vitoria row

        }else if(CheckColumnVictoryCondition(tabuleiro)!=null){ //checando condicao de vitoria Column
            return CheckColumnVictoryCondition(tabuleiro);
        }
        else if (CheckTransversalVictoryCondition(tabuleiro)!=null) { // checando condicao de vitoria transversal
            return CheckTransversalVictoryCondition(tabuleiro);
        }


        else if(CheckRowLoseCondition(tabuleiro)!=null){
            return CheckRowLoseCondition(tabuleiro);

        }else if(CheckColumnLoseCondition(tabuleiro)!=null){
            return CheckColumnLoseCondition(tabuleiro);

        } else if(CheckTransversalLoseCondition(tabuleiro)!=null) {
            return CheckTransversalLoseCondition(tabuleiro);
        }


           return MakeAMove(tabuleiro);


    }
    private int[] MakeAMove(int[][] tabuleiro) {
        //tentar vencer por transversal
         if(PlayInTransversal(tabuleiro)!=null) {
             return PlayInTransversal(tabuleiro);
         }
         //tentar vencer por linha ou coluna
         else if( PlayInLineOrColumn(tabuleiro)!=null) {
             return  PlayInLineOrColumn(tabuleiro);
         }

        else{
            return PlayRandom(tabuleiro);
         }
    }

    private int[] PlayRandom(int[][] tabuleiro) {
        int[] jogada = new int[2];
        Random r = new Random();
        int i;
        int j;
       while(true){
            i = r.nextInt(tabuleiro.length);
            j = r.nextInt(tabuleiro.length);
            if (tabuleiro[i][j] == -1) {
                jogada[0] = i;
                jogada[1] = j;
                return jogada;
            }
        }
    }

    private int[] PlayInLineOrColumn(int[][] tabuleiro) {
        int[] jogada = new int[2];
        int length = tabuleiro.length-1;

        for (int r = 0; r <= length; r++) { // for para percorrer linha

            for (int c = 0; c <= length; c++) { // for para percorrer coluna

                if (tabuleiro[r][c] != -1 && tabuleiro[r][c] != this.getSimbolo()) { // ja achou um lugar inimigo
                    break;
                }else if(tabuleiro[r][c] == -1) { // espaco vazio caso tenha condicao de vitoria
                    jogada[0] = r;
                    jogada[1] = c;
                }

                if (c == length) {
                    return jogada;
                }
            }
        }
        for (int c = 0; c <= length; c++) { // for para percorrer linha

            for (int r = 0; r <= length; r++) { // for para percorrer coluna

                if (tabuleiro[r][c] != -1 && tabuleiro[r][c] != this.getSimbolo()) { // ja achou um lugar inimigo
                    break;
                } else if(tabuleiro[r][c] == -1){ // espaco vazio caso tenha condicao de vitoria
                    jogada[0] = r;
                    jogada[1] = c;
                }
                if (r == length) {
                    return jogada;
                }
            }
        }
        return null;
    }

    private int[] PlayInTransversal(int[][] tabuleiro) {
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length-1; // tamanho do tabuleiro

        for (int r = 0; r <= length; r++) { // for para percorrer linha

            if (tabuleiro[r][r] != -1 && tabuleiro[r][r] != this.getSimbolo()) { // ja achou um lugar inimigo
                return null;
            } else { // espaco vazio caso tenha condicao de vitoria
                if (tabuleiro[r][r] == -1) {
                    jogada[0] = r;
                    jogada[1] = r;
                }
                if (r == length ) {
                    return jogada;
                }
            }
        }

            for (int r = 0; r < length; r++) { // for para percorrer linha
                int tableEnd = tabuleiro.length-1;

                if (tabuleiro[r][tableEnd] != -1 && tabuleiro[r][r] != this.getSimbolo()) {
                    return null;
                }else { // espaco vazio caso tenha condicao de vitoria
                    tableEnd--;
                    } if(tabuleiro[r][tableEnd] == -1) {
                        jogada[0] = r;
                        jogada[1] = r;
                    }
                    if (tableEnd == 0) {
                        return jogada;
                    }
            }
        return null;
        }

    private int[] CheckRowVictoryCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length; // tamanho do tabuleiro


        for(int r = 0; r <= length-1; r++){ // for para percorrer linha
            cont = 0;

            for(int c = 0; c <= length-1; c++){ // for para percorrer coluna

                if(tabuleiro[r][c]!= -1 && tabuleiro[r][c]!=this.getSimbolo()){ // ja achou um lugar inimigo
                   break;
                }
                else if(tabuleiro[r][c]==this.getSimbolo()){ // comecar a contar
                    cont++;
                }else{ // espaco vazio caso tenha condicao de vitoria
                    jogada[0]=r;
                    jogada[1]=c;
                }
            }

            if(cont == length-1 ){
                return jogada;
            }
        }
        return null;
    }

    private int[] CheckColumnVictoryCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length; // tamanho do tabuleiro

        for(int c = 0; c<= length-1; c++){ // for para percorrer linha
            cont = 0;

            for(int r = 0; r <= length-1; r++){ // for para percorrer coluna

                if(tabuleiro[r][c]!= -1 && tabuleiro[r][c]!=this.getSimbolo()){ // ja achou um lugar inimigo
                    break;
                }
                else if(tabuleiro[r][c]==this.getSimbolo()){ // comecar a contar
                    cont++;
                }else{ // espaco vazio caso tenha condicao de vitoria
                    jogada[0]=r;
                    jogada[1]=c;
                }
            }

            if(cont == length-1 ){
                return jogada;
            }
        }




        return null;
    }

    private int[] CheckTransversalVictoryCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length; // tamanho do tabuleiro

        for(int r = 0; r <= length-1; r++){ // for para percorrer linha
            cont = 0;

                if(tabuleiro[r][r]!= -1 && tabuleiro[r][r]!=this.getSimbolo()){ // ja achou um lugar inimigo
                    break;
                }
                else if(tabuleiro[r][r]==this.getSimbolo()){ // comecar a contar
                    cont++;
                }else{ // espaco vazio caso tenha condicao de vitoria
                    jogada[0]=r;
                    jogada[1]=r;
                }

            if(cont == length-1 ){
                return jogada;
            }
        }

        for(int r = 0; r <= length-1; r++){ // for para percorrer linha
            cont = 0;
             int tableEnd= tabuleiro.length-1;

            if(tabuleiro[r][tableEnd]!= -1 && tabuleiro[r][tableEnd]!=this.getSimbolo()){ // ja achou um lugar inimigo
                break;
            }
            else if(tabuleiro[r][tableEnd]==this.getSimbolo()){ // comecar a contar
                cont++;
            }else{ // espaco vazio caso tenha condicao de vitoria
                jogada[0]=r;
                jogada[1]=r;

            }
            tableEnd--;
            if(cont == length-1 ){
                return jogada;
            }
        }

        return null;
    }


    private int[] CheckRowLoseCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int length = tabuleiro.length-1; // tamanho do tabuleiro

        for(int r = 0; r <= length; r++){
            int cont=0;
            for(int c = 0; c <= length; c++){

                if(tabuleiro[r][c]==this.getSimbolo()){
                   return null;
                }
                else if(tabuleiro[r][c]!=-1){ // comecar a contar
                   cont=cont+1;
                }else if(tabuleiro[r][c]==-1){ // espaco vazio caso tenha condicao de vitoria
                    jogada[0]=r;
                    jogada[1]=c;
                }
            }
            if(cont == tabuleiro.length-1){
                return jogada;
            }

        }
        return null;
    }

    private int[] CheckColumnLoseCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length-1; // tamanho do tabuleiro

        for(int c = 0; c <= length; c++){ // for para percorrer linha
            cont = 0;

            for(int r = 0; r <= length; r++){ // for para percorrer coluna

                if(tabuleiro[r][c]==this.getSimbolo()){
                    return null;
                }
                else if(tabuleiro[r][c]!=-1){ // comecar a contar
                    cont=cont+1;
                }else if(tabuleiro[r][c]==-1){ // espaco vazio caso tenha condicao de vitoria
                    jogada[0]=r;
                    jogada[1]=c;
                }
            }
            if(cont == tabuleiro.length-1 ){
                return jogada;
            }
        }

        return null;
        }



    private int[] CheckTransversalLoseCondition(int[][] tabuleiro) {
        //first check de row and keep adding in column
        //r = row
        //c = column
        int[] jogada = new int[2];
        int cont; //inicializa contador
        int length = tabuleiro.length; // tamanho do tabuleiro

        for(int r = 0; r <= length-1; r++){ // for para percorrer linha
            cont = 0;

            if( tabuleiro[r][r]==this.getSimbolo()){ // ja achou um lugar inimigo
                return null;
            }
            else if(tabuleiro[r][r]!= -1 && tabuleiro[r][r]!=this.getSimbolo()){ // comecar a contar
                cont++;
            }else{ // espaco vazio caso tenha condicao de vitoria
                jogada[0]=r;
                jogada[1]=r;
            }

            if(cont == length-1 ){
                return jogada;
            }
        }

        for(int r = 0; r<= length-1; r++){ // for para percorrer linha
            cont = 0;
            int tableEnd= tabuleiro.length;

            if(tabuleiro[r][tableEnd-1]==this.getSimbolo()){ // ja achou um lugar inimigo
                return null;
            }
            else if(tabuleiro[r][tableEnd-1]!= -1 && tabuleiro[r][r]!=this.getSimbolo()){ // comecar a contar
                cont++;
            }else{ // espaco vazio caso tenha condicao de vitoria
                jogada[0]=r;
                jogada[1]=r;

            }
            tableEnd--;
            if(cont == length-1 ){
                return jogada;
            }
        }
        return null;
    }

}
