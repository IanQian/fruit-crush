
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ShenglanQian
 */
public class problem {
    int board_size;
    int fruit_types;
    double time_left;
    char[][] board;
    
    problem(String path){
        try {
            File f=new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(f)); 
            this.board_size=Integer.parseInt(reader.readLine());
            this.fruit_types=Integer.parseInt(reader.readLine());
            this.time_left=Double.parseDouble(reader.readLine());
            this.board=new char[board_size][board_size];
            for(int i=0;i<this.board_size;i++){
                this.board[i]=reader.readLine().toCharArray();
            }
                
        }
        catch(IOException e) {  
            e.printStackTrace();  
        } 
    }
    
   

    
}
