package GUI;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

class home extends JFrame{//JFrame���p������home(�T�u�N���X)���`
	public static void main(String[] args) {
		 home frame = new home("�^�C�g��");//�I�u�W�F�N�g�쐬
		 frame.setVisible(true);//������I�u�W�F�N�g��\��(�ӂ��͔�\���ɂȂ��Ă�)
	}	 
	
	home(String title){	//�R���X�g���N�^	 
		setTitle(title); //�^�C�g����ݒ�
		setSize(1200, 800);//���ƍ���
		 setLocationRelativeTo(null);//windows�̐^�񒆂ɕ\��
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~�{�^���ŃA�v�������
		 //setContentPane(new JLabel(new ImageIcon("./images/back.jpg")));//�t���[���̔w�i�摜
		 Image background = Toolkit.getDefaultToolkit().getImage("./images/back.jpg").getScaledInstance(1200,800,0);//�킩���
		 
		 setLayout(new FlowLayout());
		 
		/* TestPanel p1 = new TestPanel();
		 Graphics g = p1.image.getGraphics();
		 g.drawImage(background,1200,800,this);
		 add(p1);
		 p1.repaint();������*/
		 

		 JPanel p2 = new JPanel();//�p�l���̃I�u�W�F�N�g�̍쐬
		 p2.setPreferredSize(new Dimension(300,800));//�p�l���̃T�C�Y�ݒ�
		 p2.setBackground(Color.BLUE);
		 p2.setOpaque(false);//false�ɂ���Ɠ����ɂȂ�
//		 p.setLayout(new BorderLayout());
		 p2.setLayout(null);//���C�A�E�g�}�l�[�W�����g�p���Ȃ�

		 //Container contentPane = getContentPane();//�{�^���⃉�x����ǉ�����I�u�W�F�N�g��Container�N���X����擾

		 add(p2);
		 
		 JButton btn1 = new JButton("�X�^�[�g");
		 JButton btn2 = new JButton("���[��");
		 //btn1.setPreferredSize(new Dimension(200,100));
		 //btn2.setPreferredSize(new Dimension(200,100));
		 btn1.setFont(new Font("MS�S�V�b�N", Font.PLAIN, 30));
		 btn2.setFont(new Font("MS�S�V�b�N", Font.PLAIN, 30));
		 btn1.setForeground(Color.WHITE);
		 btn2.setForeground(Color.WHITE);
		 //btn2.setBackground(Color.WHITE);
		 btn1.setBounds(40,300,200,100);//���A���A���A����
		 btn2.setBounds(40,400,200,100);
		 btn1.setContentAreaFilled(false);//�{�^���̃{�[�_�[�ƕ����ȊO�𓧖��ɂ���
		 btn1.setBorderPainted(false);//�{�^���̃{�[�_�[�𓧖��ɂ���
		 btn2.setContentAreaFilled(false);//�{�^���̃{�[�_�[�ƕ����ȊO�𓧖��ɂ���
		 btn2.setBorderPainted(false);//�{�^���̃{�[�_�[�𓧖��ɂ���
		 JLabel label = new JLabel("Poker");
		 label.setFont(new Font("Century", Font.ITALIC, 100));
		 //label.setPreferredSize(new Dimension(200,100));//���x���̑傫��
		 label.setBounds(10,150,400,100);
		 p2.add(label);
		 p2.add(btn1);
		 p2.add(btn2);
		 btn1.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			    	new game("Poker");//game�̃t���[�������
			    }
		 });
		 btn2.addActionListener(new ActionListener(){//�{�^���������ꂽ��
			    public void actionPerformed(ActionEvent e) {
			    	new Message("���[������");//Message�̃t���[�������
			        //JOptionPane.showMessageDialog(null , "���[����������܂��B");                                               
			    }
		 });


	}
	

}

