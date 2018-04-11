import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Tictactoe extends JFrame implements KeyListener
{
	MyTextField tf[][]=new MyTextField[3][3]; // 9 text fields for user input
	int a[][]=new int[3][3];		  //to store 'o' as -1 and 'x' as 1
	int sums[]=new int[8];  		  //to store sums of each row,column and diagonal
	int turn=0;
	JDialog d=new JDialog();	//display the winner or error
	JLabel l=new JLabel();
	Tictactoe()
	{
		setVisible(true);
		setSize(300,300);
		setLayout(new GridLayout(3,3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i=0;i<3;i++)
		{	
			for(int j=0;j<3;j++)
			{
				tf[i][j]=new MyTextField(i,j);
				add(tf[i][j]);
				tf[i][j].addKeyListener(this);
			}
		}
		d.add(l);
	}
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar()=='o' || e.getKeyChar()=='x')
		{
			MyTextField s = (MyTextField)e.getSource();
			s.setText(String.valueOf(e.getKeyChar()));
			if(turn%2==0 && s.getText().equals("o"))
			{
				l.setText("x's turn");
				d.setVisible(true);
				d.setSize(200,200);
				return;
			}
			if(turn%2==1 && s.getText().equals("x"))
			{
				//System.out.println("wrong play");
				l.setText("o's turn");
				d.setVisible(true);
				d.setSize(200,200);
				return;
			}
			turn++;
			s.setEditable(false);
			int i=s.i , j=s.j; //gets the textField number where input is entered
			a[s.i][s.j]=s.getText().equals("o")?-1:1; //enters -1 for 'o' and 1 for 'x'
			update();
			checkWon();
		}
	}
	void update() //to update all the sums
	{
		sums[0]=a[0][0]+a[0][1]+a[0][2]; // sum of 1st row
		sums[1]=a[1][0]+a[1][1]+a[1][2]; // sum of 2nd row
		sums[2]=a[2][0]+a[2][1]+a[2][2]; // sum of 3rd row
		sums[3]=a[0][0]+a[1][0]+a[2][0]; // sum of 1st col
		sums[4]=a[0][1]+a[1][1]+a[2][1]; // sum of 2nd col
		sums[5]=a[0][2]+a[1][2]+a[2][2]; // sum of 3rd col
		sums[6]=a[0][0]+a[1][1]+a[2][2]; // sum of 1st diagonal
		sums[7]=a[0][2]+a[1][1]+a[2][0]; // sum of 2nd diagonal
	}
	void checkWon()
	{
		int i=0;
		while(i<8 && sums[i]!=3 && sums[i]!=-3)
			i++;
		if(i==8)
			return;
		if(sums[i]==3) //3=>three (1)s / three 'x's
		{
			l.setText("x won");
			d.setVisible(true);
			d.setSize(200,200);
		}
		if(sums[i]==-3) //3=>three (-1)s / three '0's
		{
			l.setText("o won");
			d.setVisible(true);
			d.setSize(200,200);
		}
	}
	public void keyPressed(KeyEvent ke1){}
	public void keyReleased(KeyEvent ke2){}
	public static void main(String args[])
	{
		new Tictactoe();
	}
}
class MyTextField extends JTextField{
	int i;int j;
	MyTextField(int i,int j)
	{
		this.i=i;
		this.j=j;
	}
}