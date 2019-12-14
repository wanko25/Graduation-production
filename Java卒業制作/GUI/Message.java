package GUI;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Message extends JFrame{	
	Message(String title){
		setTitle(title); //�^�C�g����ݒ�
		setSize(900,690);//���ƍ���
		setLocationRelativeTo(null);//windows�̐^�񒆂ɕ\��
		setContentPane(new JLabel(new ImageIcon("./images/cardframe.jpg")));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~�{�^���ŃA�v������適�A�v���܂ŕ���Ƃ��߂��������
		
		setLayout(new FlowLayout());
		
		JPanel p = new JPanel();//�p�l���̃I�u�W�F�N�g�̍쐬
		p.setPreferredSize(new Dimension(600,550));//�p�l���̃T�C�Y�ݒ�
		p.setOpaque(false);//�p�l���𓧖���
		p.setLayout(new BorderLayout());
		add(p);
		JTextArea area = new JTextArea();
		area.setLineWrap(true);//�e�L�X�g��܂�Ԃ�
		area.setFont(new Font("�l�r �S�V�b�N", Font.PLAIN, 17));
		area.setEditable(false);//false�Ńe�L�X�g�G���A��ҏW�ł��Ȃ��悤�ɂ���
		area.append("\r\n\r\n\r\n\r\n1.�Q�[���J�n���̎莝���̃|�C���g�͂O����ł��B\r\n");
		area.append("2.�J�[�h��1�l�T���z��܂��B\r\n");
		area.append("3.��D�̃J�[�h����D���Ȗ������̂āA���̖������̃J�[�h�������̎�D�ɉ����܂��B\r\n");
		area.append("4.���ɉ����ă|�C���g�����Z����A���̃|�C���g�𑝂₵�Ă����Q�[���ł��B\r\n");
		area.append("����D�̃J�[�h���̂āA�V�����J�[�h�������邱�Ƃ��ł���̂�1��ł��B\r\n\r\n");
		area.append("\r\n���ɂ��ā��ア��\r\n");
		area.append("�����y�A(2Point)�F�����������Q������\r\n");
		area.append("�c�[�y�A(5Point)�F�����y�A���Q�g����\r\n");
		area.append("�X���[�J�[�h(10Point)�F�����������R������\r\n");
		area.append("�X�g���[�g(20Point)�F�T���̐������A�����ď��Ԃɑ����i�u1.2.3.4.5�v�u11.12.13.1.2�v�Ȃǁj\r\n");
		area.append("�t���b�V��(25Point)�F�T���̃}�[�N���S������\r\n");
		area.append("�t���n�E�X(30Point)�F�X���[�J�[�h�ƃ����y�A������\r\n");
		area.append("�t�H�[�J�[�h(35Point)�F�����������S������\r\n");
		area.append("�X�g���[�g�t���b�V��(60Point)�F�������A�����ď��Ԃɑ����A�}�[�N������\r\n");
		area.append("���C�����X�g���[�g�t���b�V��(100Point)�F�X�y�[�h�́u10.11.12.13.1�v�������B\r\n\r\n");
		p.add(area,BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
