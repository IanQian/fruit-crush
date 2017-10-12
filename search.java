

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ShenglanQian
 */
public class search {
    int count=0;
    int sum=0;
    
    public void run(problem pro){
        node root=new node(pro.board,0,1);
     
     
        action res=Max_value(root,2,Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println(res.value); 
        System.out.println(res.move);
        /*System.out.println(res.value);
         for(int i=0;i<pro.board_size;i++){
            for(int j=0;j<pro.board_size;j++){
                System.out.print(root.move_board[i][j]+"  ");
            }
            System.out.println();
        }*/

        write_result(res.move,root);
        System.out.print(count+"  "+sum);
        
        
       
    }
    public action Max_value(node root,int depth,int alpha,int beta){
        action res=new action(0,Integer.MIN_VALUE);sum++;
        if(depth==0){
            return get_max(root);
        }
        if(root.move_size==1){
            return new action(0,root.point);
            
        }
        res.value=Integer.MIN_VALUE;
        
        for(int i=1;i<=root.move_size;i++){
            node next=root.get_successor(i);
            action max=Min_value(next,depth-1,alpha,beta);
            
            if(res.value<max.value){
                res.value=max.value;
                res.move=i;
            }
            if(res.value>=beta){
                count++;
                return res;
            }
            alpha=Math.max(alpha, res.value);
        }
         return res;   
    }
     public action Min_value(node root,int depth,int alpha,int beta){
        action res=new action(0,Integer.MAX_VALUE);sum++;

        if(depth==0){
            
            return get_max(root);
        }
        if(root.move_size==1){
            return new action(0,root.point);
            
        }
        res.value=Integer.MAX_VALUE;
        for(int i=1;i<=root.move_size;i++){
            node next=root.get_successor(i);
            action min=Max_value(next,depth-1,alpha,beta);
            if(res.value>min.value){
                res.value=min.value;
            }
            if(res.value<=alpha){
                count++;
                return res;
            }
            beta=Math.min(beta, res.value);
        }
        return res;
     }
 
    
    public action get_max(node n){
        action res=new action(0,Integer.MIN_VALUE);
        HashMap<Integer,Integer> table=new HashMap();
        for(int i=0;i<n.move_board.length;i++){
            for(int j=0;j<n.move_board.length;j++){
                if(n.move_board[i][j]!=0){
                    if(table.containsKey(n.move_board[i][j])){
                    table.replace(n.move_board[i][j], table.get(n.move_board[i][j])+2);
                    }
                    else{
                        table.put(n.move_board[i][j], 2);
                    }
                }
            }
        }
        
        
        for(Map.Entry<Integer, Integer> entry : table.entrySet()){
            if(entry.getValue()>res.value){
                res.value=entry.getValue();
                res.move=entry.getKey();
            }
        }
        res.value=n.point+res.value*n.type;
        
        return res;
        
    }
    public void write_result(int move,node root){
         try{
            File outf= new File("output.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(outf));
            for(int i=0;i<root.move_board.length;i++){
                for(int j=0;j<root.move_board.length;j++){
                    if(root.move_board[i][j]==move){
                        bw.write((char)('A'+j));
                        bw.write((char)'0'+i+1);
                        bw.newLine();
                        root=root.get_successor(move);
                        
                        for(int p=0;p<root.move_board.length;p++){
                            for(int q=0;q<root.move_board.length;q++){
                                bw.write(root.cur_board[p][q]);
                            }
                        
                            bw.newLine();
                        }


                    bw.close();
                        return;
                    }
                }
            }
            
        }
        catch (IOException e) {  
            e.printStackTrace();  
        } 
        
    }
}
