package Graduation_production;
import java.util.*;
public class Poker
{
	String[] card = {"��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13"};
			 /*���̓n�[�g�A���̓_�C���A���̓N���[�o�[�A���̓X�y�[�h*/
	Scanner sc;
	String select;//�X�^�[�g�����[���̑I��
	Random random;//�����_���ɃJ�[�h�ԍ��擾
	boolean flag;//�J�[�h���d�����Ă��邩
	int[] rv;//�z��J�[�h�̓Y��������z��
	String[] tcard;//�z��ꂽ�J�[�h�������Ă���z��
	int[] suuji;//�J�[�h�̐�������
	String[] mark;//�J�[�h�̃}�[�N����
	int count;//���������̖���
	boolean Jflag;//�J�[�h�����Ԃɕ���ł��邩
	boolean Mflag;//�}�[�N���������ǂ���
	boolean Sflag;//�}�[�N�����ׂăX�y�[�h��
	int[] rs = {1,10,11,12,13};//suuji�̒��g��10.11.12.13.1�ɂȂ��Ă��邩
	int[] change;//�J�[�h��ւ���Ƃ��̕ϐ�
	String henko;//�J�[�h��ς��邩�ǂ���

	public void show()//�������J�n��
	{
		System.out.println("Poker");
		System.out.println("�X�^�[�g");
		System.out.println("���[��\n");

		while(true){
			System.out.println("�X�^�[�g(s)�����[��(r)��I�����Ă��������B");

			sc = new Scanner(System.in);
			select = sc.nextLine();

			if(select.equals("s") || select.equals("S")){
				System.out.println("\n�Q�[�����J�n���܂��B\n");
				break;
			}
			else if(select.equals("r") || select.equals("R")){
				System.out.println("\n���[���̐������J�n���܂��B\n");
				System.out.println("1.�Q�[���J�n���̎莝���̃|�C���g�͂O����ł��B");
				System.out.println("2.�J�[�h��1�l�T���z��܂��B");
				System.out.println("3.��D�̃J�[�h����D���Ȗ������̂āA���̖������̃J�[�h�������̎�D�ɉ����܂��B");
				System.out.println("4.���ɉ����ă|�C���g�����Z����A���̃|�C���g�𑝂₵�Ă����Q�[���ł��B");
				System.out.println("����D�̃J�[�h���̂āA�V�����J�[�h�������邱�Ƃ��ł���̂�1��ł��B\n");
				System.out.println("���ɂ��ā��ア��");
				System.out.println("�����y�A(2Point)�F�����������Q������");
				System.out.println("�c�[�y�A(5Point)�F�����y�A���Q�g����");
				System.out.println("�X���[�J�[�h(10Point)�F�����������R������");
				System.out.println("�X�g���[�g(15Point)�F�T���̐������A�����ď��Ԃɑ����i�u1.2.3.4.5�v�u11.12.13.1.2�v�Ȃǁj");
				System.out.println("�t���b�V��(20Point)�F�T���̃}�[�N���S������");
				System.out.println("�t���n�E�X(25Point)�F�X���[�J�[�h�ƃ����y�A������");
				System.out.println("�t�H�[�J�[�h(30Point)�F�����������S������");
				System.out.println("�X�g���[�g�t���b�V��(35Point)�F�������A�����ď��Ԃɑ����A�}�[�N������");
				System.out.println("���C�����X�g���[�g�t���b�V��(40Point)�F�X�y�[�h�́u10.11.12.13.1�v�������B\n\n");
				
			}
			else{
				System.out.println("\n�X�^�[�g�����[���̂ǂ��炩����͂��Ă��������B\n");
			}

		}
	}
	
	public void distribute()//�J�[�h��z��
	{
		random = new Random();
		rv = new int[5];

		//flag = false;
		do{
			flag = false;

			for(int i=0; i<5; i++){
				rv[i] = random.nextInt(51);//�z��J�[�h�̓Y����z��ɓ����
			}

			for(int i=0; i<5; i++){
				for(int a=i+1; a<5; a++){
					if(rv[i] == rv[a]){//�����J�[�h�����邩
						flag = true;
					}
				}
			}

		}while(flag);//true(�����J�[�h������Ƃ�)�Ƀ��[�v����

		tcard = new String[5];
		for(int i=0; i<5; i++){
			tcard[i] = card[rv[i]];
			System.out.print(i+1 + "." + tcard[i] + "     ");//�J�[�h��z��
		}

		System.out.println("\n�����̓n�[�g�A���̓_�C���A���̓N���[�o�[�A���̓X�y�[�h\n");

	}

	public void divide()//��D�̃J�[�h���}�[�N�Ɛ����ɕ����ď����ɕ��ׂ�
	{
		suuji = new int[5];
		mark = new String[5];

		for(int i=0; i<5; i++){
			//suuji[i] = tcard[i].substring(1);
			suuji[i] = Integer.parseInt(tcard[i].substring(1));//��D�̃J�[�h�𐔎��ɕ�����
			mark[i] = tcard[i].substring(0,1);//��D�̃J�[�h���}�[�N�ɕ�����
		}
		
		Arrays.sort(suuji);//�����ɕ��בւ�
	}
	
	public void same_number()//���������̖����Ɩ�
	{
		count = 0;
		for(int i=0; i<5; i++){
			for(int a=i+1; a<5; a++){
				if(suuji[i] == suuji[a]){
					count = count + 1;
				}
			}
		}
		switch(count){
			case 0:
				System.out.println("���Ȃ��ł��B");
			break;

			case 1://�����y�A
				System.out.println("�����y�A�ł��B");
			break;

			case 2://�c�[�y�A
				System.out.println("�c�[�y�A�ł��B");
			break;

			case 3://�X���[�J�[�h
				System.out.println("�X���[�J�[�h�ł��B");
			break;

			case 4://�t���n�E�X
				System.out.println("�t���n�E�X�ł��B");
			break;

			case 6://�t�H�[�J�[�h
				System.out.println("�t�H�[�J�[�h�ł��B");
			break;
		}

	}
	
	public void straight()//�����X�g���[�g���i����������ł邩�j
	{
		Jflag = true;//true�̎��̓J�[�h������ł���
		for(int i=0; i<4; i++){//�X�g���[�g
			if(suuji[i] != suuji[i+1]-1){//���̃J�[�h�͏��Ԓʂ肩
				if(suuji[0] == 1  &&  suuji[4] == 13){
					if(suuji[i+1] != 13-(3-i)){
						Jflag = false;
						break;
					}
					continue;//if�̊O�ɏo��
				}
				Jflag = false;
				break;//for�̊O�ɏo��
			}
		}
	}
	
	public void same_mark()//�}�[�N��������
	{
		Mflag = true;//true�̎��̓}�[�N������
		loop: for(int i=0; i<5; i++){//�}�[�N��������
			for(int a=i+1; a<5; a++){
				if(!(mark[i].equals(mark[a]))){
					Mflag = false;
					break loop;//���x����loop�̊O���ɏo��

				}
			}
		}
	}
	
	public void role()//�}�[�N�Ɛ���������ł���J�[�h�̖�
	{
		if(Jflag && Mflag){
			System.out.println("�X�g���[�g�t���b�V���ł��B");
		}
		else if(Jflag){//true�̎�
			System.out.println("�X�g���[�g�ł��B");
		}
		else if(Mflag){
			System.out.println("�t���b�V���ł��B");
		}
	}
	
	public void royal_straight_flush()//���C�����X�g���[�g�t���b�V����
	{
		Sflag = true;//�}�[�N�����ׂăX�y�[�h��
		for(int i=0; i<5; i++){
			if(!(mark[i].equals("��"))){
				Sflag = false;
				break;
			}
		}
		for(int i=0; i<5; i++){//������10.11.12.13.1�ɂȂ��Ă��邩
			if(suuji[i] != rs[i]){
				Sflag = false;
				break;
			}
		}
		if(Sflag){
			System.out.println("���C�����X�g���[�g�t���b�V���ł��B");
		}
	}
	
	
	
	public static void main(String[] args)
	{


		
		
		
		System.out.println("\n\n�J�[�h��ւ��܂����H");
		System.out.println("���ւ������ꍇ��Y�A�ւ��Ȃ��ꍇ��N����͂��Ă��������B");

		henko = sc.nextLine();
		
		while(true){
			if(henko.equals("Y") || henko.equals("y")){
				break;
			}
			else if(henko.equals("N") || henko.equals("n")){
				System.out.println("");
				break;
			}
			else{
				System.out.println("Y��N�̂ǂ��炩����͂��Ă��������B");
			}
		}

		System.out.println("\n�ւ������J�[�h�̔ԍ���I��ł��������B");


		String kae = sc.nextLine();//�ւ������J�[�h�̔ԍ����擾
		kae = kae.replaceAll("�@", "");//�S�p�X�y�[�X���󕶎��ɒu��
		kae = kae.replaceAll(" ", ""); //�����̊Ԃ̔��p�X�y�[�X���󕶎��ɕϊ�
		int len = kae.length();//���͂����ԍ��̐�
		change = new int[len];//���͂����ԍ����i�[����z��쐬

		for (int i=0; i<len; i++) {
			change[i] = Character.getNumericValue(kae.charAt(i));//kae��char�^�Ŏ��o��int�^�ɕϊ�
			//change[i] = Integer.parseInt(kae.substring(i, i + 1));//kae�̐���z�񂩂�1�����o���Ċi�[������ł�ok
			if(change[i] < 1  ||  change[i] > 5){//������1~5�̊Ԃ�
				System.out.println("1�`5�̐�������͂��Ă��������B");
			}
		}
		Arrays.sort(change);//���͂��ꂽ�����������ɕ��בւ�
		for(int i=0; i<len; i++){
			for(int a=0; a<5; a++){
				if(change[i] == a+1){
					rv[a] = random.nextInt(51);//�ԍ��Ŏw�肳�ꂽ�J�[�h���������_���Ŕz��Ɋi�[
				}
			}
		}





		for(int i=0; i<5; i++){
			tcard[i] = card[rv[i]];
			System.out.print(i+1 + "." + tcard[i] + "     ");//�J�[�h��z��
		}
	}
}
