/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ShenglanQian
 */
public class node {
    char[][] cur_board;
    int[][] move_board;
    int point;
    int move_size;
    int type; // 1=max; -1=min
    node(char[][] x,int point,int type){
        this.cur_board=new char[x.length][x.length];
        for(int i=0;i<x.length;i++){
            for(int j=0;j<x.length;j++){
                this.cur_board[i][j]=x[i][j];
            }
        }
        this.move_board=this.move_board(x);
        this.point=point;
        this.type=type;
    }
    /*node(){
        this.cur_board=new char[x.cur_board.length][x.cur_board.length];
        for(int i=0;i<x.cur_board.length;i++){
            for(int j=0;j<x.cur_board.length;j++){
                this.cur_board[i][j]=x.cur_board[i][j];
            }
        }
        this.move_board=this.move_board(this.cur_board);
        this.point=x.point;
        this.type=x.point;
    }*/
    
     public char[][] stand(){
        char[][] res=new char[this.cur_board.length][this.cur_board.length];
        for(int i=0;i<this.cur_board.length;i++){
            int index=this.cur_board.length-1;
            for(int j=this.cur_board.length-1;j>=0;j--){
                if(this.cur_board[j][i]!='*'){
                    res[index][i]=this.cur_board[j][i];index--;
                }
            }
            while(index>=0){
                res[index][i]='*';index--;
            }
        }
        return res;
    }
    public int[][] move_board(char[][] cur_board){
        int index=1;
        int[][] res=new int[cur_board.length][cur_board.length];
        for(int i=0;i<cur_board.length;i++){
            for(int j=0;j<cur_board.length;j++){
                if(res[i][j]==0&&cur_board[i][j]!='*'){
                    check(i,j,cur_board,res,cur_board[i][j],index);
                    index++;
                }
            }
        }
        this.move_size=--index;
        return res;
    }
    
    public void check(int i,int j,char[][] cur_board,int[][] move_board,char check_num,int index){
        if(i<0||j<0||j>=cur_board.length||i>=cur_board.length){
            
        }
        else if(move_board[i][j]==0&&cur_board[i][j]==check_num){
            move_board[i][j]=index;
            check(i,j+1,cur_board,move_board,check_num,index);
            check(i,j-1,cur_board,move_board,check_num,index);
            check(i-1,j,cur_board,move_board,check_num,index);
            check(i+1,j,cur_board,move_board,check_num,index);
        }
        
    }
    
    public node get_successor(int index){
        int value=0;
        node next=new node(this.cur_board,this.point,this.type);
        for(int i=0;i<next.cur_board.length;i++){
            for(int j=0;j<next.cur_board.length;j++){
                if(next.move_board[i][j]==index){
                    next.cur_board[i][j]='*';
                    value+=2;
                }
            }
        }
        next.cur_board=next.stand();
        next.move_board=next.move_board(next.cur_board);
        next.point+=next.type*value;
        next.type*=-1;
        return next;
        
    }
}
