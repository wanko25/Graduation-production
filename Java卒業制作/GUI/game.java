package GUI;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class game extends JFrame{
	game(String title){
		setTitle(title); //�^�C�g����ݒ�
		setSize(1200, 800);//���ƍ���
		setLocationRelativeTo(null);//windows�̐^�񒆂ɕ\��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~�{�^���ŃA�v�������
		setContentPane(new JLabel(new ImageIcon("./images/green.jpg")));//�t���[���̔w�i�摜
		
		setLayout(new FlowLayout());
		
		setVisible(true);
	}
}
