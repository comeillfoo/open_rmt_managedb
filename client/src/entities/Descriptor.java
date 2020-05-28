package entities;

import java.io.*;
import java.util.ArrayList;

public class Descriptor {
    private final String SCRIPTS_PATH;

    /**
     * Конструктор, в котором формируется путь к скриптам.
     */
    public Descriptor() {
        SCRIPTS_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "scripts";
    }

    /**
     * Метод перевода текста скрипта в ArrayList.
     * @param fileName
     * @return ArrayList &lt;String&gt;
     * @throws IOException
     */
    public ArrayList<String> discript(String fileName) throws IOException{
        String fullPathToScript = SCRIPTS_PATH + System.getProperty("file.separator") + (fileName.contains(".pt") ? fileName : fileName + ".pt");
        ArrayList<String> scriptParts = new ArrayList<>();
        String tempLine = "";
        try (
                FileInputStream fileInputStream = new FileInputStream(fullPathToScript);
                InputStreamReader rawReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(rawReader);
        ){
            while (bufferedReader.ready()) {
                tempLine = bufferedReader.readLine();
                if(!tempLine.equals("")){
                    scriptParts.add(tempLine);
                }
            }
        }catch (FileNotFoundException e) {
            throw new FileNotFoundException("Не удалось найти файл по указанному имени");
        } catch (IOException e) {
            throw  new IOException("Не удалось получить доступ к потоку ввода для чтения файла",e);
        }
        return scriptParts;
    }
}