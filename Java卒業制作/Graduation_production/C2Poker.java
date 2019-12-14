package Graduation_production;
import java.util.*;
public class C2Poker
{
	String[] card = {"��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13",
			 "��1","��2","��3","��4","��5","��6","��7","��8","��9","��10","��11","��12","��13"};
			 /*���̓n�[�g�A���̓_�C���A���̓N���[�o�[�A���̓X�y�[�h*/
	String[] Pcard = {"��A","��2","��3","��4","��5","��6","��7","��8","��9","��10","��J","��Q","��K",
			 "��A","��2","��3","��4","��5","��6","��7","��8","��9","��10","��J","��Q","��K",
			 "��A","��2","��3","��4","��5","��6","��7","��8","��9","��10","��J","��Q","��K",
			 "��A","��2","��3","��4","��5","��6","��7","��8","��9","��10","��J","��Q","��K"};
	Scanner sc;
	String select;//�X�^�[�g�����[���̑I��
	Random random;//�����_���ɃJ�[�h�ԍ��擾
	int[] rv;//�z��J�[�h�̓Y��������z��
	String[] tcard;//�z��ꂽ�J�[�h�������Ă���z��
	int[] suuji;//�J�[�h�̐�������
	String[] mark;//�J�[�h�̃}�[�N����
	int count;//���������̖���
	boolean flag;//���ڂ̃J�[�h��z��Ƃ��ɏ������΂��Ƃ��Ɏg���t���O
	boolean Jflag;//�J�[�h�����Ԃɕ���ł��邩
	boolean Mflag;//�}�[�N���������ǂ���
	boolean Sflag;//�}�[�N�����ׂăX�y�[�h��
	int[] rs = {1,10,11,12,13};//suuji�̒��g��10.11.12.13.1�ɂȂ��Ă��邩
	int[] change;//�J�[�h��ւ���Ƃ��̕ϐ�
	String henko;//�J�[�h��ς��邩�ǂ���
	int len;//change�̔z��̒���
	int point;//�|�C���g�̃J�E���g
	int[] before;//change�ł����������̈ȑO�̃J�[�h�̔ԍ�
	int beforenum;//before�̔z��Ɋi�[����Ƃ��̓Y���Ɏg��
	boolean changeCardFlag;	//�d�����m�F������true

	C2Poker(){//�R���X�g���N�^
		changeCardFlag = false;
		random = new Random();
		flag = true;
		rv = new int[5];
		for(int i=0;i<5;i++) {
			rv[i] = random.nextInt(51);//��ԍŏ��ɔz��\��̃J�[�h��z��ɓ����
		}
		len = 5;
		change = new int[len];		//len�����������܂��傤
		before = new int[len];
		beforenum = 0;
		point = 0;
	}
	
	
	private void start() {
		show();
		check();
		judgment();
		change();
		judgment();
		point();
	}
	
	public void judgment() {//���𔻒肷��	
		divide();
		straight();
		same_mark();
		royal_straight_flush();
		role();
		same_number();
	}
		
		
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
				System.out.println("�X�g���[�g(20Point)�F�T���̐������A�����ď��Ԃɑ����i�u1.2.3.4.5�v�u11.12.13.1.2�v�Ȃǁj");
				System.out.println("�t���b�V��(25Point)�F�T���̃}�[�N���S������");
				System.out.println("�t���n�E�X(30Point)�F�X���[�J�[�h�ƃ����y�A������");
				System.out.println("�t�H�[�J�[�h(35Point)�F�����������S������");
				System.out.println("�X�g���[�g�t���b�V��(60Point)�F�������A�����ď��Ԃɑ����A�}�[�N������");
				System.out.println("���C�����X�g���[�g�t���b�V��(100Point)�F�X�y�[�h�́u10.11.12.13.1�v�������B\n\n");
				
			}
			else{
				System.out.println("\n�X�^�[�g�����[���̂ǂ��炩����͂��Ă��������B\n");
			}

		}
	}
	
	public void check() {//�J�[�h�����Ԃ��Ă��邩��ׂăJ�[�h��z��
		do {
			changeCardFlag = false;
			for(int i=0; i<5; i++){
				for(int a=i+1; a<5; a++){
					if(rv[i] == rv[a]){//�����J�[�h�����邩
						changeCardFlag = true;
						duplication(i,a);//duplication���\�b�h���Ăяo��
					}
				}	
			}
		}while(changeCardFlag);
		
		flag = false;
		tcard = new String[5];
		for(int c=0; c<5; c++){
			tcard[c] = card[rv[c]];//��card�ɃJ�[�h������
			before[c] = rv[c];//�ς���J�[�h�̈ȑO�̃J�[�h�����Ă���
			System.out.print(c+1 + "." + Pcard[rv[c]] + "     ");//�J�[�h��z��
		}
		
		
		
		
		
		/*tcard[0] = card[39];
		System.out.print(1 + "." + Pcard[39] + "     ");//�J�[�h��z��
		
		for(int i=1;i<5;i++) {
			tcard[i] = card[47+i];
			System.out.print(i+1 + "." + Pcard[47+i] + "     ");//�J�[�h��z��
		}*/

		
		
		
		
		
		System.out.println("\n�����̓n�[�g�A���̓_�C���A���̓N���[�o�[�A���̓X�y�[�h\n");
	}
	
	public void duplication(int i,int a) {//change�̔z��ɓ����Ă���J�[�h�ƌ��������J�[�h�����Ԃ��Ă��邩
		
		for(int b=0; b<5; b++) {
			if(flag) {
				rv[a] = random.nextInt(51);//���ڂɃJ�[�h��z�����Ƃ��ɂ��Ԃ��Ă�������̃J�[�h��ς���
			}
			else if(i == change[b]-1) {//�ς���J�[�h�̔ԍ������Ԃ��Ă��邩
				if(change[b] == 0) {//�ς���J�[�h�̔ԍ���0(�����l)�������ꍇ�͏������΂�
					continue;
				}
				rv[i] = random.nextInt(51);//���Ԃ��Ă�̂Ə����l��0����Ȃ��ꍇ�A���Ԃ��Ă���ԍ��̃J�[�h��ς���
			}
			else {
				rv[a] = random.nextInt(51);//���Ԃ��ĂȂ���������̃J�[�h��ς���
			}
		}
	}

	public void divide()//��D�̃J�[�h���}�[�N�Ɛ����ɕ����ď����ɕ��ׂ�
	{
		suuji = new int[5];
		mark = new String[5];

		for(int i=0; i<5; i++){                                                                           
			suuji[i] = Integer.parseInt(tcard[i].substring(1));//��D�̃J�[�h�𐔎��ɕ�����
			mark[i] = tcard[i].substring(0,1);//��D�̃J�[�h���}�[�N�ɕ�����
		}
		
		Arrays.sort(suuji);//�����ɕ��בւ�
	}
	
	public void same_number()//���������̖����Ɩ�
	{
		if(point != 0 ) return;  
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
				point = 2;
			break;

			case 2://�c�[�y�A
				System.out.println("�c�[�y�A�ł��B");
				point = 5;
			break;

			case 3://�X���[�J�[�h
				System.out.println("�X���[�J�[�h�ł��B");
				point = 10;
			break;

			case 4://�t���n�E�X
				System.out.println("�t���n�E�X�ł��B");
				point = 30;
			break;

			case 6://�t�H�[�J�[�h
				System.out.println("�t�H�[�J�[�h�ł��B");
				point = 35;
			break;
		}
	}
	
	public void straight()//�����X�g���[�g���i����������ł邩�j
	{		
		Jflag = true;//true�̎��̓J�[�h������ł���
		
		for(int i=0; i<4; i++){//�X�g���[�g
			if(suuji[i] != suuji[i+1]-1){//���̃J�[�h�͏��Ԓʂ肩
				if(suuji[0] == 1  &&  suuji[4] == 13){//1����n�܂�13�ŏI��邩�ǂ���
					if(suuji[i+1] != 13-(3-i)){//1,10,11,12,13���ǂ���
						Jflag = false;//����ł��Ȃ�
						break;//for�̊O�ɏo��(straight�̏������I���)
					}
					continue;//if�̊O�ɏo��
				}
				Jflag = false;//����ł��Ȃ�
				break;//for�̊O�ɏo��(straight�̏������I���)
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
		if(point != 0 ) return;  
		if(Jflag && Mflag){
			System.out.println("�X�g���[�g�t���b�V���ł��B");
			point = 60;
		}
		else if(Jflag){//true�̎�
			System.out.println("�X�g���[�g�ł��B");
			point = 20;
		}
		else if(Mflag){
			System.out.println("�t���b�V���ł��B");
			point = 25;
		}
	}
	
	public void royal_straight_flush()//���C�����X�g���[�g�t���b�V����
	{
		if(point != 0 ) return;  
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
			point = point + 100;
		}
	}
	
	public void change() {//�ς������J�[�h����������
		System.out.println("\n\n�J�[�h��ւ��܂����H");
		System.out.println("���ւ������ꍇ��Y�A�ւ��Ȃ��ꍇ��N����͂��Ă��������B");
	
		while(true){
			henko = sc.nextLine();//�ύX�̓���
			
			if(henko.equals("Y") || henko.equals("y")){
				point = 0;
				store();//store���\�b�h���Ăяo��
				check();//check���\�b�h���Ăяo��
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

	}
	
	public void store() {//len��change�̒l����ׂĊi�[����
		System.out.println("\n�ւ������J�[�h�̔ԍ���I��ł��������B");
		String kae = sc.nextLine();//�ւ������J�[�h�̔ԍ����擾
		kae = kae.replaceAll("�@", "");//�S�p�X�y�[�X���󕶎��ɒu��
		kae = kae.replaceAll(" ", ""); //�����̊Ԃ̔��p�X�y�[�X���󕶎��ɕϊ�
		len = kae.length();//���͂����ԍ��̐�


		for (int i=0; i<len; i++) {
			change[i] = Character.getNumericValue(kae.charAt(i));//kae��char�^�Ŏ��o��int�^�ɕϊ�
			//change[i] = Integer.parseInt(kae.substring(i, i + 1));//kae�̐���z�񂩂�1�����o���Ċi�[������ł�ok
			if(change[i] < 1  ||  change[i] > 5){//������1~5�̊Ԃ�
				System.out.println("1�`5�̐�������͂��Ă��������B");
			}
		}
		Arrays.sort(change);//���͂��ꂽ�����������ɕ��בւ�(5��菭�Ȃ��Ƃ��͕ς���ԍ��̑O��change�̏����l�̂O������)
		/*for(int i=0; i<5; i++){
			for(int a=0; a<5; a++){
				if(change[i] == 0) {//change�̔z��̏����l(0)����ς���Ă��Ȃ������̏������΂�
					continue;
				}
				else if(change[i] == a+1){
					rv[a] = random.nextInt(51);//�ԍ��Ŏw�肳�ꂽ�J�[�h���������_���Ŕz��Ɋi�[
				}
			}
		}		
	
		
		for(int i=0;i<len;i++) {
			before[i] = rv[i];//��������O�̃J�[�h�̔ԍ���z��ɓ����
		}
		before = new int[len];*/
		
		for(int i=0; i<5; i++){
			if(change[i] == 0) {//change�̔z��̏����l(0)����ς���Ă��Ȃ������̏������΂�
				continue;
			}
			else {//change��0�ȊO��������
				//before[beforenum] = rv[change[i]-1];//��������O�̃J�[�h�̔ԍ���z��ɓ����
				rv[change[i]-1] = random.nextInt(51);//�ԍ��Ŏw�肳�ꂽ�J�[�h���������_���Ŕz��Ɋi�[
				/*System.out.println(before[beforenum] + " " + rv[change[i]-1]);
				beforenum = beforenum + 1;
				if(before[change[i]-1] == rv[change[i]-1]) {//�z��ꂽ�J�[�h�������������̃J�[�h�Ɠ�������Ȃ�����ׂ�
					rv[change[i]-1] = random.nextInt(51);//�����������������x�����_���Ŕz��Ɋi�[
				}*/
			}
		}
		//comparison();
	}
	
	/*public void comparison() {//�z��ꂽ�J�[�h�������������̃J�[�h�Ɠ�������Ȃ�����ׂ�
		for(int i=0; i<5; i++){
			for(int a=0; a<5; a++){
				if(before[i] == rv[a]) {//�z��ꂽ�J�[�h���O�Ɠ�����
					rv[a] = random.nextInt(51);//������x�����ɂȂ����J�[�h�̔ԍ��������_���Ɋi�[
					//changeCardFlag = true;
				}
			}
		}
	}*/
	
	
	
	
	
	
	
	
	public void point() {//�|�C���g������
		System.out.println("����̊l���|�C���g��" + point + "�|�C���g�ł��B");
	}
	
	public static void main(String[] args)
	{


		C2Poker poker = new C2Poker();//�I�u�W�F�N�g�쐬
		poker.start();
		
	}
}
		



