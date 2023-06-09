package javaapplication3;
public class JavaApplication3 {
    
    public static  class ComLineParser {
        
        private String[] keys; // ключи
        private String[] delimeters; // разделители "/", "-"
        
        public enum SwitchStatus { NoError, Error, ShowUsage }
        
        public ComLineParser(String[] keys) {   // конструктор класса с 1 параметром
            this(keys, new String[] { "/", "-" });
        }
                                                // конструктор класса с 2 параметрами
        public ComLineParser(String[] keys, String[] delimeters) {
            this.keys = keys;
            this.delimeters = delimeters;
        }

        public void OnUsage(String errorKey){
            if (errorKey != null)
            System.out.println("Command-line switch error:" + errorKey);
            System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
            System.out.println(" -? показать Help файл");
            System.out.println(" -r задать имя входного файла");
            System.out.println(" -w выполнить вывод в указанный файл");
        }
        
        public SwitchStatus OnSwitch(String key, String keyValue) {
            System.out.println(key+" "+keyValue);
            return SwitchStatus.NoError;
        }

        public Boolean Parse(String[] args) {
            SwitchStatus ss = SwitchStatus.NoError;
            int argNum;
            for (argNum = 0; (ss == SwitchStatus.NoError) && (argNum < args.length); argNum++) {
                // проверка наличия правильного разделителя
                boolean isDelimeter = false;
                for (int n = 0; !isDelimeter && (n < delimeters.length); n++) {
                    isDelimeter = args[argNum].regionMatches(0,delimeters[n], 0, 1);
                }
                if (isDelimeter) {
                    // проверка наличия правильного ключа
                    Boolean isKey = false;
                    int i;
                    for (i = 0; !isKey && (i < keys.length); i++) {
                        isKey = args[argNum].toUpperCase().regionMatches(1,
                        keys[i].toUpperCase(),0,keys[i].length());
                        if (isKey) break;
                    }
                    if (!isKey) {
                        ss = SwitchStatus.Error;
                        break;
                    } else {
                        ss = OnSwitch(keys[i].toLowerCase(),
                        args[argNum].substring(1 + keys[i].length()));
                    }
                }
                else {
                    ss = SwitchStatus.Error;
                    break;
                }
            }
            // завершение разбора командной строки
            if (ss == SwitchStatus.ShowUsage) OnUsage(null);
            if (ss == SwitchStatus.Error) OnUsage((argNum == args.length) ? null : args[argNum]);
            return ss == SwitchStatus.NoError;
        }

    }
    public static void main(String[] args) {
                                                   
        String[] keys1 = {"?", "-r", "-w"}; // создание массива ключей 
        ComLineParser obj1 = new ComLineParser(keys1); //создайте объект класса «ComLineParser» 
                                                       //со следующим набором ключей "?","r","w"
                                                       //и разделителями по умолчанию.
        obj1.Parse(args);
        System.out.print("Результат работы:\n");
        String[] keys2 = {"?", "r", "w"};  // создание массива ключей keys2     
        ComLineParser obj2 = new ComLineParser(keys2);
        obj2.Parse(args);// Вызовите на этом объекте метод «Parse», 
                         //передав ему массив параметров «args».
    }
}
