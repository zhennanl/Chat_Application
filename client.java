import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class client extends JFrame implements ActionListener {
    JPanel panel;
    JTextField textfield;
    JButton button;

    static JTextArea textArea;
    static Socket s;
    static DataInputStream dataInput;
    static DataOutputStream dataOutput;

    client(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(7, 94, 84));
        panel.setBounds(0, 0, 450, 70);
        add(panel);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("pic/arrow.jpg"));
        Image image2 = image1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label1 = new JLabel(image3);
        label1.setBounds(5, 17, 30, 30);
        panel.add(label1);

        label1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource("pic/rameses.png"));
        Image image5 = image4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon image6 = new ImageIcon(image5);
        JLabel label2 = new JLabel(image6);
        label2.setBounds(40, 5, 60, 60);
        panel.add(label2);

        ImageIcon image7 = new ImageIcon(ClassLoader.getSystemResource("pic/video.png"));
        Image image8 = image7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image9 = new ImageIcon(image8);
        JLabel label5 = new JLabel(image9);
        label5.setBounds(290, 20, 30, 30);
        panel.add(label5);

        ImageIcon image11 = new ImageIcon(ClassLoader.getSystemResource("pic/audio.png"));
        Image image12 = image11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon image13 = new ImageIcon(image12);
        JLabel label6 = new JLabel(image13);
        label6.setBounds(350, 20, 35, 30);
        panel.add(label6);

        ImageIcon image14 = new ImageIcon(ClassLoader.getSystemResource("pic/icon.png"));
        Image image15 = image14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon image16 = new ImageIcon(image15);
        JLabel label7 = new JLabel(image16);
        label7.setBounds(410, 20, 13, 25);
        panel.add(label7);

        JLabel label3 = new JLabel("Rameses");
        label3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        label3.setForeground(Color.WHITE);
        label3.setBounds(110, 15, 100, 18);
        panel.add(label3);

        JLabel label4 = new JLabel("Active Now");
        label4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        label4.setBounds(110, 35, 100, 20);
        panel.add(label4);

        textArea = new JTextArea();
        textArea.setBounds(5, 75, 440, 570);
        textArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(textArea);

        textfield = new JTextField();
        textfield.setBounds(5, 655, 310, 40);
        textfield.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(textfield);

        button = new JButton("Send");
        button.setBounds(320, 655, 123, 40);
        button.setBackground(new Color(7, 94, 84));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        button.addActionListener(this);
        add(button);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(450, 700);
        setLocation(1100, 200);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            String out = textfield.getText();
            textArea.setText(textArea.getText()+"\n\t\t\t"+out);
            dataOutput.writeUTF(out);
            textfield.setText("");
        }
        catch(Exception e){}
    }

    public static void main(String[] args){
        new client().setVisible(true);

        try{
            s = new Socket("127.0.0.1", 6001);
            dataInput = new DataInputStream(s.getInputStream());
            dataOutput = new DataOutputStream(s.getOutputStream());

            String messageInput = "";

            messageInput = dataInput.readUTF();
            textArea.setText(textArea.getText()+"\n"+messageInput);

        }catch(Exception e){}
    }
}