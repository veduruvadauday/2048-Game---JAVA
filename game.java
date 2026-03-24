import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class game implements ActionListener
{
   JFrame j=new JFrame("2048game");
   int boo,s=0,s2=0;
   Boolean boo1=false;
   JButton a,b,e,d,f;
   JTextField t1,t2,t3;//t1 - high score,t2-score
   JButton g[][]=new JButton[6][6];
   Random r=new Random();

   private int randomTileValue() {
      return (r.nextInt(10) < 9) ? 2 : 4;
   }

   /** Adjacent same non-empty tiles can still merge (game not over). */
   private static boolean tileMergeable(String a, String b) {
      if (" ".equals(a) || " ".equals(b)) return false;
      return a.equals(b);
   }

   game()
  {
    j.setLayout(new FlowLayout());
    a=new JButton("up");
    b=new JButton("down");
    e=new JButton("left");
    d=new JButton("right");
    f=new JButton("NEW GAME");

   t1=new JTextField("0");
   try
{  
   Class.forName("com.mysql.jdbc.Driver");

   Connection con=DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true","root","");

   Statement stmt=con.createStatement();

   ResultSet rs=stmt.executeQuery("select * from userdata");

   if(rs.next())
   t1.setText(""+rs.getString(1));

   con.close();

}catch(Exception e){
   System.out.println("High score DB unavailable (game still runs): " + e.getMessage());
}


     t2=new JTextField("0");
     t3=new JTextField("Add to get 2048");
      JPanel p=new JPanel();
      JPanel p1=new JPanel();
      JPanel p2=new JPanel();
      JPanel p3=new JPanel();
      JPanel p4=new JPanel();
    JLabel highScoreLabel = new JLabel("High Score:");
    JLabel ScoreLabel = new JLabel("Score:");
   p.setLayout(new GridLayout(1,4));
   p1.setLayout(new GridLayout(4,4));
   p2.setLayout(new GridLayout(1,2));
   p3.setLayout(new GridLayout(1,1));
   p4.setLayout(new GridLayout(1,1));
  p.add(a);
  p.add(b);
  p.add(e);
  p.add(d);
  p3.add(f);
  a.addActionListener(this);
  e.addActionListener(this);
  d.addActionListener(this);
  b.addActionListener(this);
  f.addActionListener(this);
    try
 { 
    for(int i=0;i<4;i++)
   {
      for(int j=0;j<4;j++)
     {
           
       g[i][j]=new JButton(" ");
       p1.add(g[i][j]);
     }
   }  
 }catch(ArrayIndexOutOfBoundsException ww){}

   p2.add(highScoreLabel);
   p2.add(t1);
   p2.add(ScoreLabel);
   p2.add(t2);
   p4.add(t3); 
   j.add(p2);
   j.add(p1);
   j.add(p);
   j.add(p3);
   j.add(p4);
   t1.setEditable(false);
   t2.setEditable(false);
   t3.setEditable(false);
   j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   j.setSize(420, 520);
   j.setLocationRelativeTo(null);
   j.setVisible(true);
 } //swing frame close


public static void main(String args[])
 {       
   
SwingUtilities.invokeLater(new Runnable()
  {
    public void run()
   {
      new game();
   }});

}

public void data()
{

try{
Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true","root","");

   String sqlk ="UPDATE userdata SET uname = ?";
PreparedStatement pstmt=con.prepareStatement(sqlk);
   pstmt.setString(1,""+s);
	
  int x=pstmt.executeUpdate();
   con.close();
  
}catch(Exception e){ 
e.printStackTrace();
}  
     
}

public void actionPerformed(ActionEvent ee)
 {
     String u=ee.getActionCommand();
     int ii=2;
     int i=0,j=0;
     if(u.equals("NEW GAME"))
    {   s=0;
         t2.setText(""+s);
   s2=Integer.parseInt(t1.getText());
          t3.setText("Add to get 2048");
       try
       { 
          for(int mm=0;mm<4;mm++)
         {
          for(int nn=0;nn<4;nn++)         //setting all labels to empty
          {
              g[mm][nn].setText(" ");
          }
         }
         while(ii>0)
        {             
            if(ii==2)
            { i = r.nextInt(4);
               j = r.nextInt(4);
             g[i][j].setText(""+randomTileValue());
             ii--; 
            }
             if(ii==1)
            { int il= r.nextInt(4);
              int jl= r.nextInt(4);
               
              if((i==il)&&(j==jl))
             {
              
              }
              else
              {
                g[il][jl].setText(""+randomTileValue());
                ii--;
               }
             }  
          
        }  //while close
      }catch(NumberFormatException aa){System.out.println(aa);}
    }  //if close
  
 
   else if(u.equals("left"))
   { 
        try
       {     
           for(i=0;i<4;i++)
         {
           for(j=0;j<4;j++)
          {
              String h=g[i][j].getText();

              if(h.equals(" "))
             { 
	continue;
             }  
             else
            {  
                int z=Integer.parseInt(h);
                int k=j;
                while(k>0)
                {
	   if(g[i][k-1].getText().equals(" "))
	 {
                     g[i][k-1].setText(""+z);
	    g[i][k].setText(" ");
	 }
	  k--;
                } //while close
            } //else close
          } // for close
       } //for close

      


        
      for(int i1=0;i1<4;i1++)
    {
       for(int j1=0;j1<3;j1++)
      {         
           String h1;
           String h2;
           h1=(g[i1][j1].getText());
           if(h1.equals(" "))
          { 
            break; 
           }  
 
           else
          { 
            int k1=j1;
            int z1,z2;
             z1=Integer.parseInt(h1);             
             h2=(g[i1][j1+1].getText());
             if(h2.equals(" "))
            {
              break;
             } 
             else
           {
             z2=Integer.parseInt(h2);   
             if(z1==z2)
            {  
               int h3=z1+z2;
               if(k1==0)
              { 
                g[i1][k1].setText(""+h3);              
                g[i1][k1+1].setText(""+g[i1][k1+2].getText());
                g[i1][k1+2].setText(""+g[i1][k1+3].getText());
                g[i1][k1+3].setText(" "); 
              }
              if(k1==1)
             {
               g[i1][k1].setText(""+h3);
               g[i1][k1+1].setText(""+g[i1][k1+2].getText());
               g[i1][k1+2].setText(" ");
             }
             if(k1==2)
             {
               g[i1][k1].setText(""+h3);
               g[i1][k1+1].setText(" ");
             }
             if(h3==2048)
             t3.setText(" WON ");
               s+=h3;
            int s1=Integer.parseInt(t2.getText()); 
            t2.setText(""+s);           
             if(s2<=s1)
            {
      t1.setText(""+s);     
  
	data();  //high score update
              s2=s;
             }          
           }  	  // if close
          }               		  
          }	//else close			
         }	//for close		
        }        //for close

       for(boo=1;boo<=1;boo++)
      {   
           int    ii2 = r.nextInt(4);
           int    jj2 = r.nextInt(4);
           boolean tt=false;
            for(int i11=0;i11<=3;i11++)
           for(int j11=0;j11<=3;j11++)  
         {
           if((g[i11][j11].getText()).equals(" "))
           {
             tt=true;
             break; 
            }
         }
          if(tt)
        {
           if(g[ii2][jj2].getText().equals(" "))
          {
            g[ii2][jj2].setText(""+randomTileValue());
          }
          else
          {   
             boo--;
           }
        }else{ boolean bb=false;
               for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int yy = y + 1;
                if (tileMergeable(g[x][y].getText(), g[x][yy].getText())) {
                  bb=true;
                }
            }
        }
         
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                int xx = x + 1;
                if (tileMergeable(g[x][y].getText(), g[xx][y].getText())) {
                  bb=true;
                }
            }
        } 
            if(!bb)
           {
	g[0][0].setText(" ");
	g[0][1].setText(" ");
	g[0][2].setText(" ");
	g[0][3].setText(" ");
                 g[1][0].setText("G");
	g[1][1].setText("A");
	g[1][2].setText("M");
	g[1][3].setText("E");
	g[2][0].setText("O");
	g[2][1].setText("V");
	g[2][2].setText("E");
	g[2][3].setText("R");
                 g[3][0].setText(" ");
	g[3][1].setText(" ");
	g[3][2].setText(" ");
	g[3][3].setText(" ");
                 t3.setText("Press NEWGAME");
	break;
            }
          	
                   break;
                 } //tt else close
       }
         
 
       }catch(NumberFormatException aa){System.out.println(aa);}
           catch(ArrayIndexOutOfBoundsException ss){System.out.println(ss);} //try close
     }  //else close left

  else if(u.equals("right"))
   { 
        try
       {     
           for(i=0;i<4;i++)
         {
           for(j=3;j>=0;j--)
          {
              String h=g[i][j].getText();

              if(h.equals(" "))
             { 
	continue;
             }  
             else
            {  
                int z=Integer.parseInt(h);
                int k=j;
                while(k<3)
                {
	   if(g[i][k+1].getText().equals(" "))
	 {
                     g[i][k+1].setText(""+z);
	    g[i][k].setText(" ");
	 }
	  k++;
                } //while close
            } //else close
          } // for close
       } //for close

      


        
      for(int i1=0;i1<4;i1++)
    {
       for(int j1=3;j1>=1;j1--)
      {         
           String h1;
           String h2;
           h1=(g[i1][j1].getText());
           if(h1.equals(" "))
          { 
            break; 
           }  
 
           else
          { 
            int k1=j1;
            int z1,z2;
             z1=Integer.parseInt(h1);             
             h2=(g[i1][j1-1].getText());
             if(h2.equals(" "))
            {
              break;
             } 
             else
           {
             z2=Integer.parseInt(h2);   
             if(z1==z2)
            {  
               int h3=z1+z2;
               if(k1==3)
              { 
                g[i1][k1].setText(""+h3);              
                g[i1][k1-1].setText(""+g[i1][k1-2].getText());
                g[i1][k1-2].setText(""+g[i1][k1-3].getText());
                g[i1][k1-3].setText(" "); 
              }
              if(k1==2)
             {
               g[i1][k1].setText(""+h3);
               g[i1][k1-1].setText(""+g[i1][k1-2].getText());
               g[i1][k1-2].setText(" ");
             }
             if(k1==1)
             {
               g[i1][k1].setText(""+h3);
               g[i1][k1-1].setText(" ");
             }
             if(h3==2048)
             t3.setText(" WON ");
             s=s+h3;
             int s1=Integer.parseInt(t2.getText()); 
            t2.setText(""+s);           
             if(s2<=s1)
            {
              t1.setText(""+s);
	data();
              s2=s;    
             }       
           }  	  // if close
          }               		  
          }	//else close			
         }	//for close		
        }        //for close

       for(boo=1;boo<=1;boo++)
      {
           int    ii2 = r.nextInt(4);
           int    jj2 = r.nextInt(4);
             boolean tt=false;
            for(int i11=0;i11<=3;i11++)
           for(int j11=0;j11<=3;j11++)  
         {
           if((g[i11][j11].getText()).equals(" "))
           {
             tt=true;
             break; 
            }
         }
          if(tt)
        {
           if(g[ii2][jj2].getText().equals(" "))
          {
            g[ii2][jj2].setText(""+randomTileValue());
          }
          else
          {   
             boo--;
           }
        }else{
		boolean bb=false;
                                                                          for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int yy = y + 1;
                if (tileMergeable(g[x][y].getText(), g[x][yy].getText())) {
                  bb=true;
                }
            }
        }
         
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                int xx = x + 1;
                if (tileMergeable(g[x][y].getText(), g[xx][y].getText())) {
                  bb=true;
                }
            }
        } 
            if(!bb)
           {
	g[0][0].setText(" ");
	g[0][1].setText(" ");
	g[0][2].setText(" ");
	g[0][3].setText(" ");
                 g[1][0].setText("G");
	g[1][1].setText("A");
	g[1][2].setText("M");
	g[1][3].setText("E");
	g[2][0].setText("O");
	g[2][1].setText("V");
	g[2][2].setText("E");
	g[2][3].setText("R");
                 g[3][0].setText(" ");
	g[3][1].setText(" ");
	g[3][2].setText(" ");
	g[3][3].setText(" ");
	t3.setText("Press NEWGAME");
	break;
            }
                  break;
                 }  
          
       }
         
 
       }catch(NumberFormatException aa){System.out.println(aa);}
           catch(ArrayIndexOutOfBoundsException ss){System.out.println(ss);} //try close
     }  //else close right
 
   else if(u.equals("up"))
   { 
        try
       {     
           for(j=0;j<4;j++)
         {
           for(i=0;i<4;i++)
          {
              String h=g[i][j].getText();

              if(h.equals(" "))
             { 
	continue;
             }  
             else
            {  
                int z=Integer.parseInt(h);
                int k=i;
                while(k>0)
                {
	   if(g[k-1][j].getText().equals(" "))
	 {
                     g[k-1][j].setText(""+z);
	    g[k][j].setText(" ");
	 }
	  k--;
                } //while close
            } //else close
          } // for close
       } //for close

      


        
      for(int j1=0;j1<4;j1++)
    {
       for(int i1=0;i1<3;i1++)
      {         
           String h1;
           String h2;
           h1=(g[i1][j1].getText());
           if(h1.equals(" "))
          { 
            break; 
           }  
 
           else
          { 
            int k1=i1;
            int z1,z2;
             z1=Integer.parseInt(h1);           
             h2=(g[k1+1][j1].getText());
             if(h2.equals(" "))
            {
              break;
             } 
             else
           {
             z2=Integer.parseInt(h2);   
             if(z1==z2)
            {  
               int h3=z1+z2;
               if(k1==0)
              { 
                g[k1][j1].setText(""+h3);              
                g[k1+1][j1].setText(""+g[k1+2][j1].getText());
                g[k1+2][j1].setText(""+g[k1+3][j1].getText());
                g[k1+3][j1].setText(" "); 
              }
              if(k1==1)
             {
               g[k1][j1].setText(""+h3);
               g[k1+1][j1].setText(""+g[k1+2][j1].getText());
               g[k1+2][j1].setText(" ");
             }
             if(k1==2)
             {
               g[k1][j1].setText(""+h3);
               g[k1+1][j1].setText(" ");
             }
             if(h3==2048)
             t3.setText(" WON ");
           s=s+h3;
                       int s1=Integer.parseInt(t2.getText()); 
            t2.setText(""+s);           
             if(s2<=s1)
            {
              t1.setText(""+s);
	data();
              s2=s;
             }          
           }  	  // if close
          }               		  
          }	//else close			
         }	//for close		
        }        //for close

       for(boo=1;boo<=1;boo++)
      {
           int    ii2 = r.nextInt(4);
           int    jj2 = r.nextInt(4);
            boolean tt=false;
            for(int i11=0;i11<=3;i11++)
           for(int j11=0;j11<=3;j11++)  
         {
           if((g[i11][j11].getText()).equals(" "))
           {
             tt=true;
             break; 
            }
         }
          if(tt)
        {
           if(g[ii2][jj2].getText().equals(" "))
          {
            g[ii2][jj2].setText(""+randomTileValue());
          }
          else
          {   
             boo--;
           }
        }else{
                          boolean bb=false;
                                                                          for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int yy = y + 1;
                if (tileMergeable(g[x][y].getText(), g[x][yy].getText())) {
                  bb=true;
                }
            }
        }
         
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                int xx = x + 1;
                if (tileMergeable(g[x][y].getText(), g[xx][y].getText())) {
                  bb=true;
                }
            }
        } 
            if(!bb)
           {
	g[0][0].setText(" ");
	g[0][1].setText(" ");
	g[0][2].setText(" ");
	g[0][3].setText(" ");
                 g[1][0].setText("G");
	g[1][1].setText("A");
	g[1][2].setText("M");
	g[1][3].setText("E");
	g[2][0].setText("O");
	g[2][1].setText("V");
	g[2][2].setText("E");
	g[2][3].setText("R");
                 g[3][0].setText(" ");
	g[3][1].setText(" ");
	g[3][2].setText(" ");
	g[3][3].setText(" ");
	t3.setText("Press NEWGAME");
	break;
            }
                 break;
                 }
           
       }
         
 
       }catch(NumberFormatException aa){System.out.println(aa);}
           catch(ArrayIndexOutOfBoundsException ss){System.out.println(ss);} //try close
     }  //else close up


else if(u.equals("down"))
   { 
        try
       {     
           for(j=0;j<4;j++)
         {
           for(i=3;i>=0;i--)
          {
              String h=g[i][j].getText();

              if(h.equals(" "))
             { 
	continue;
             }  
             else
            {  
                int z=Integer.parseInt(h);
                int k=i;
                while(k<3)
                {
	   if(g[k+1][j].getText().equals(" "))
	 {
                     g[k+1][j].setText(""+z);
	    g[k][j].setText(" ");
	 }
	  k++;
                } //while close
            } //else close
          } // for close
       } //for close

      


        
      for(int j1=0;j1<4;j1++)
    {
       for(int i1=3;i1>=1;i1--)
      {         
           String h1;
           String h2;
           h1=(g[i1][j1].getText());
           if(h1.equals(" "))
          { 
            break; 
           }  
 
           else
          { 
            int k1=i1;
            int z1,z2;
             z1=Integer.parseInt(h1);           
             h2=(g[k1-1][j1].getText());
             if(h2.equals(" "))
            {
              break;
             } 
             else
           {
             z2=Integer.parseInt(h2);   
             if(z1==z2)
            {  
               int h3=z1+z2;
               if(k1==3)
              { 
                g[k1][j1].setText(""+h3);              
                g[k1-1][j1].setText(""+g[k1-2][j1].getText());
                g[k1-2][j1].setText(""+g[k1-3][j1].getText());
                g[k1-3][j1].setText(" "); 
              }
              if(k1==2)
             {
               g[k1][j1].setText(""+h3);
               g[k1-1][j1].setText(""+g[k1-2][j1].getText());
               g[k1-2][j1].setText(" ");
             }
             if(k1==1)
             {
               g[k1][j1].setText(""+h3);
               g[k1-1][j1].setText(" ");
             }
             if(h3==2048)
             t3.setText(" WON ");
                s=s+h3;
             int s1=Integer.parseInt(t2.getText()); 
            t2.setText(""+s);           
             if(s2<=s1)
            {
              t1.setText(""+s);
	data();
              s2=s;
             }    //score if          
           }  	  // if close
          }               		  
          }	//else close			
         }	//for close		
        }        //for close

       for(boo=1;boo<=1;boo++)
      {
           int    ii2 = r.nextInt(4);
           int    jj2 = r.nextInt(4);
              boolean tt=false;
            for(int i11=0;i11<=3;i11++)
           for(int j11=0;j11<=3;j11++)  
         {
           if((g[i11][j11].getText()).equals(" "))
           {
             tt=true;
             break; 
            }
         }
          if(tt)
        {
           if(g[ii2][jj2].getText().equals(" "))
          {
            g[ii2][jj2].setText(""+randomTileValue());
          }
          else
          {   
             boo--;
           }
        }else{
                   boolean bb=false;
            for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int yy = y + 1;
                if (tileMergeable(g[x][y].getText(), g[x][yy].getText())) {
                  bb=true;
                }
            }
        }
         
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                int xx = x + 1;
                if (tileMergeable(g[x][y].getText(), g[xx][y].getText())) {
                  bb=true;
                }
            }
        } 
            if(!bb)
           {   
	g[0][0].setText(" ");
	g[0][1].setText(" ");
	g[0][2].setText(" ");
	g[0][3].setText(" ");
                 g[1][0].setText("G");
	g[1][1].setText("A");
	g[1][2].setText("M");
	g[1][3].setText("E");
	g[2][0].setText("O");
	g[2][1].setText("V");
	g[2][2].setText("E");
	g[2][3].setText("R");
                 g[3][0].setText(" ");
	g[3][1].setText(" ");
	g[3][2].setText(" ");
	g[3][3].setText(" ");
	t3.setText("Press NEWGAME");
	break;
            }
                  break;
                 } 
       }
         
 
       }catch(NumberFormatException aa){System.out.println(aa);}
           catch(ArrayIndexOutOfBoundsException ss){System.out.println(ss);} //try close
     }  //else close down
  }     // actionPerformed close
}     //main close