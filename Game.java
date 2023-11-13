import java.util.Scanner;
public class Game{
	public static void Menu(){
		System.out.println("1. Новая игра");
		System.out.println("2. Выйти");
		Scanner scan = new Scanner (System.in);
		int choice = scan.nextInt();
		if (choice == 1){
		}
		else if (choice == 2){
			Exit();
		}
		else {
			System.out.println("Некорректное значение, попробуйте снова:");
			Menu();
		}
	}
	public static int Size( ){//размеры поля
		System.out.println("Введите ширину поля");
		Scanner scan = new Scanner (System.in);
		int size = scan.nextInt();
    	return size;
    }

	public static String [][] Field(int size){//создание поля
    	String [][] field1= new String[size][size];
    	for (int i = 0; i < size; i++) {
    		for (int j = 1; j <= size; j++) {
    			field1[i][j-1] = Integer.toString(i*size+j);
    		}
    	}
    	return field1;
    	
    }
    public static void FieldOutput(String[][] field, int size){
    	String str;
		for (int i = 0; i < size; i++) { 
			str="";
			for (int j = 0; j < size; j++) {
				if (field[i][j].length() != 3 ){
					for (int h = 1; h <= (3 - (field[i][j].length())); h ++ ){
						System.out.print(" ");
						str += "_";
					}
				}
				System.out.print(field[i][j]);
				System.out.print(" | ");
				for (int k = 1; k <= (field[i][j].length() + 3); k ++){
					str+="_";

				}
			}	
			System.out.println("");
			System.out.println(str);	
		}
    }
    public static void Regime(String [][]field, int size){//выюор режима
    	Scanner scan = new Scanner (System.in);
		System.out.println("Выберите режим игры (введите номер режима)");
    	System.out.println("1) игра с другом          2) игра с компьютером");
    	int regime = scan.nextInt();
		if (regime == 1){
			Regime1(field,1,size);
		}
		else if (regime == 2){
			Regime2(field, size); 	
		}
		else { 
			System.out.println("Некорректное значение, попробуйте снова:");
			Regime(field,size);
		}
	}
	public static void Regime1(String [][]field, int sign, int size){//поменять void 
		//если нет выигрышных / нечейных ситуаций
		if (AreThereAnyFreeCells(field, size) && (WinningSituations(field, size, 'X') == false) && (WinningSituations(field, size, '0') == false)){
		//прописать действия при выигрышных / нечейных ситуациях
		FieldOutput(field, size);
		Scanner scan = new Scanner (System.in);
		if (sign==1){
			System.out.println("Ваш ход - x .Выберите номер незанятой ячейки");
			int number=scan.nextInt();
			if (Run(field, size, "X", number)){
				Regime1(field,2,size);
			}
			else {
			System.out.println("Выберите другую ячейку");
			Regime1(field,1,size);
			}

		}
		else {
			System.out.println("Ваш ход - 0 .Выберите номер незанятой ячейки");
			int number=scan.nextInt();
			 if (Run(field, size, "0", number)){
				Regime1(field,1,size);
			}
			else{
				System.out.println("Выберите другую ячейку");
				Regime1(field,2,size);
			}
		}
		}
	}

	public static int getRandomNumber(int size){
      return (int) (Math.random() * (size * size) + 1);
  	}

  	public static void ComputerRunning(String[][] field, int size){
  		int number = getRandomNumber(size);
  		if (Run(field, size, "0", number)){
			System.out.println("Ход компьютера:");
			FieldOutput(field, size);
			Regime2(field, size);
		}else{
			ComputerRunning(field, size);
		}
  	}
  	public static boolean Run(String[][] field, int size, String player, int number){
  		
			int t1=(number-1)/size;
			int t2=number-((number-1)/size)*size;
			if (field[t1][t2-1]!="X" && field[t1][t2-1]!="0"){
				field[t1][t2-1]= player ;
				return true;
			}else{
				return false;
			}
  	}
	public static void Regime2(String[][] field, int size){
		Scanner scan = new Scanner(System.in);
		if (AreThereAnyFreeCells(field, size) && (WinningSituations(field, size, 'X') == false) && (WinningSituations(field, size, '0') == false)){
			FieldOutput(field, size);
			System.out.println("Ваш ход - X.Выберите номер незанятой ячейки");
			int number=scan.nextInt();
			if (Run(field, size, "X", number)){
				if (AreThereAnyFreeCells(field, size) && (WinningSituations(field, size, 'X') == false) && (WinningSituations(field, size, '0') == false)){
					ComputerRunning(field, size);
				}else{
					//победил человек или ничья
				}
			}
			else {
			System.out.println("Выберите другую ячейку:");
			Regime2(field,size);
			}
		}
	}


	public static void Exit(){//функция выхода из игры с помощью меню
		
		System.out.println("Вы точно хотите выйти из игры?");
			System.out.println("1. Да");
			System.out.println("2. Нет");
			Scanner scan = new Scanner (System.in);
			int choice2 = scan.nextInt();
			if (choice2 == 1){
				System.exit(0);
			}else if (choice2 == 2){
				Menu();
			}else{
				System.out.println("Некорректное значение, попробуйте снова:");
				Exit();
			}
	}
	public static boolean AreThereAnyFreeCells(String [][] Field, int n){ //функция, считающая, есть ли еще свободные ячейки; n - размер поля 
		boolean FreeCells = false;
		for (int i = 0; i < n; i ++){
			for (int j = 0; j < n; j ++){
				if ((Field[i][j].charAt(0) >= '1') && (Field[i][j].charAt(0) <= '9')){
					FreeCells = true;
					break;
				}
			}
		}
		return FreeCells; //если свободные ячейки есть - true
	}
	public static boolean WinningSituations(String [][] Field, int n, char player){//функция, ищущая есть ли выигрышные ситуации для одного из игроков; char playerv- символ, за который играет игрок
		boolean win = true;
		for (int i = 0; i < n; i ++){
			if (Field[i][i].charAt(0) != player){
				win = false;
			}
		}
		if (win) {
			return true;
		}
		win = true;
		for (int i = 0; i < n; i ++){
			if (Field[i][n - 1 - i].charAt(0) != player){
				win = false;
			}
		}
		if (win){
			return true;
		}
		for (int i = 0; i < n; i ++){
			win = true;
			for (int j = 0; j < n; j ++){
				if (Field[i][j].charAt(0) != player){
					win = false;
					break;
				}
			}
			if (win){
				return true;
			}
		}
		for (int i = 0; i < n; i ++){
			win = true;
			for (int j = 0; j < n; j ++){
				if (Field[j][i].charAt(0) != player){
					win = false;
					break;
				}
			}
			if (win){
				return true;
			}
		}
		
		return false;
	}
	public static void main(String[] args){
		System.out.println("Добро пожаловать в игру Крeстики - Нолики !");
		Menu();
		int size=Size();
		String [][] field=Field(size);
		Regime(field,size);

	}
}
