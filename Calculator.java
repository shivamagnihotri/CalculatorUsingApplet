import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.*;

public class Calculator extends JApplet {

    String operator="";
    float FirstNum;
    float SecondNum;
    String PressedKey;
    boolean IsResultShown=false;
   
    public void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().setBackground( Color.red );
        JTextField display=new JTextField();
        display.setText("0");
        display.setSize(200,100);
        add(display);
        JPanel buttonPanel=new JPanel();
        buttonPanel.setSize(300,300);
        buttonPanel.setLayout(new GridLayout(5, 4));
        JButton btns[]=new JButton[21];
        String keytexts1[]={"C","/","*","-","+"};
        String keytexts2[]={"0",".","="};
        for(int i=1;i<=20;i++)
        {   
            btns[i]=new JButton();
            btns[i].setSize(80, 50);
            btns[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            buttonPanel.add(btns[i]);
            if(i<4)
                {
                btns[i].setText("");
                btns[i].setEnabled(false);
                }
            else if(i>4&&i%4!=0&&i<16)
                btns[i].setText(Integer.toString(i%4+3*((i/4)-1)));
            else if(i%4==0)
                btns[i].setText(keytexts1[(i/4)-1]);
            else
                btns[i].setText(keytexts2[(i%4)-1]);
            if(i>3)
            {
            btns[i].addActionListener(new ActionListener() {
         
                public void actionPerformed(ActionEvent e) {
                    PressedKey=((JButton)e.getSource()).getText();
                    if(PressedKey.equals("C"))
                        {
                        FirstNum=0;
                        SecondNum=0;
                        display.setText("0");
                        operator="";
                        }
                    else if(PressedKey.equals("/")||PressedKey.equals("*")||PressedKey.equals("-")||PressedKey.equals("+"))
                        {
                        operator=PressedKey;
                        FirstNum=Float.parseFloat(display.getText());
                        display.setText("0");
                        }
                    else if(PressedKey.equals("="))
                        {
                        IsResultShown=true;
                        SecondNum=Float.parseFloat(display.getText());
                        float result=0.0f;
                        if(operator.equals("+"))
                            result=FirstNum+SecondNum;
                        else if(operator.equals("-"))
                            result=FirstNum-SecondNum;
                        else if(operator.equals("*"))
                            result=FirstNum*SecondNum;
                        else if(operator.equals("/"))
                            result=FirstNum/SecondNum;
                        display.setText(Float.toString(result));
                        }
                    else if(PressedKey.equals("."))
                        {
                        if(IsResultShown)
                            {
                        	display.setText("0");
                                IsResultShown=false;
                            }
                        if(display.getText().contains(".")==false)
                        	display.setText(display.getText()+".");
                        }
                    else 
                        {
                        if(IsResultShown||display.getText().equals("0"))
                            {
                        	display.setText("");
                                IsResultShown=false;
                            }
                        display.setText(display.getText()+PressedKey);
                        }
                }
            });
            }
        }
        add(buttonPanel);   
    }
}