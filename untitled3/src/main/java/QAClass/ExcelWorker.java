package QAClass;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ExcelWorker {

    private static String[] List = {"Имя", "Фамилия", "Отчество", "Возраст",
            "Пол", "Дата Рождения", "ИНН", "Почтовый индекс",
            "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Новый лист");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i != List.length; ++i) {
            row.createCell(i).setCellValue(List[i]);
        }
        int size = 1 + (int) (Math.random() * 30);
        List<DataModel> dataList = fillData(size);
        for (DataModel data : dataList) {
            createSheetHeader(sheet, ++rowNum, data);
        }
        File file = new File("list.xls");
        try {
            System.setErr(new PrintStream(new File("log.txt")));
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("Файл создан:" + file.getAbsolutePath());
    }

    private static String readFile(String path, int line_number) {
        String fileContent = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String sub;
            int i = 1;
            while ((sub = br.readLine()) != null) {
                if (i == line_number) {
                    fileContent = sub;
                    break;
                }
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private static void writeName(DataModel data) {
        if (randBetween(0, 1) == 1) {
            String name = readFile("src/main/resources/MaleName.txt", randBetween(1, 30));
            data.setName(name);
            data.setSex("М");
        } else {
            String name = readFile("src/main/resources/FemaleName.txt", randBetween(1, 30));
            data.setName(name);
            data.setSex("Ж");
        }
    }

    private static void writeSurname(DataModel data) {
        if (data.getSex().equals("М")) {
            String surname = readFile("src/main/resources/MaleSurname.txt", randBetween(1, 30));
            data.setSurname(surname);
        } else {
            String surname = readFile("src/main/resources/FemaleSurname.txt", randBetween(1, 30));
            data.setSurname(surname);
        }
    }

    private static void writePatronymic(DataModel data) {
        if (data.getSex().equals("М")) {
            String patronymic = readFile("src/main/resources/MalePatronymic.txt", randBetween(1, 30));
            data.setSatronymic(patronymic);
        } else {
            String patronymic = readFile("src/main/resources/FemalePatronymic.txt", randBetween(1, 30));
            data.setSatronymic(patronymic);
        }
    }

    private static void writeCountry(DataModel data) {
        String country = readFile("src/main/resources/Countries.txt", randBetween(1, 30));
        data.setCountry(country);
    }

    private static void writeCity(DataModel data) {
        String city = readFile("src/main/resources/Cities.txt", randBetween(1, 30));
        data.setCity(city);
    }

    private static void writeStreet(DataModel data) {
        String street = readFile("src/main/resources/Streets.txt", randBetween(1, 30));
        data.setStreet(street);
    }

    private static void writeRegion(DataModel data) {
        String region = readFile("src/main/resources/Regions.txt", randBetween(1, 30));
        data.setRegion(region);
    }

    private static void writeDOB(DataModel data) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1920, 2000);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_MONTH, dayOfYear);
        data.setDOB(gc);
    }

    private static void writeAge(DataModel data) {
        Calendar calendar = Calendar.getInstance();
        int age = calendar.get(Calendar.YEAR) - data.getDOB().get(Calendar.YEAR);
        if (((data.getDOB().get(Calendar.MONTH)) - calendar.get(Calendar.MONTH) >= 0) & (((data.getDOB().get(Calendar.DAY_OF_MONTH)) - calendar.get(Calendar.DAY_OF_MONTH) >= 0))) {
            data.setAge(age - 1);
        } else data.setAge(age);
    }

    private static void writePostcode(DataModel data) {
        data.setPostcode(randBetween(1000000, 10000000 - 1));
    }

    private static void writeApartment(DataModel data) {
        data.setApartment(randBetween(1, 100));
    }

    private static void writeHouse(DataModel data) {
        data.setHouse(randBetween(1, 100));
    }

    private static void writeTIN(DataModel data) {
        int branchNumber = randBetween(10, 51);
        long itn = 770000000000L + branchNumber * 100000000L;
        int k = 100;
        for (int i = 1; i <= 6; ++i) {
            int n = randBetween(0, 9);
            itn += n * k;
            k *= 10;
        }
        int[] array = new int[10];
        k = 1;
        for (int i = 0; i != 10; ++i) {
            array[i] = (int) (itn / 100) % (10 * k);
            k *= 10;
        }
        int[] secondIndex = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum2 = 0;
        for (int i = 0; i != 10; ++i) {
            sum2 = secondIndex[i] * array[i];
        }
        int secondKnt = sum2 % 11;
        itn += secondKnt * 10;

        int[] firstIndex = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum1 = 0;
        for (int i = 0; i != 10; ++i) {
            sum1 = firstIndex[i] * array[i];
        }

        sum1 = firstIndex[10] * secondKnt;

        int firstKnt = sum1 % 11;
        itn += firstKnt;
        data.setTIN(itn);
    }

    private static List<DataModel> fillData(int size) {
        List<DataModel> data = new ArrayList<>();
        for (int i = 0; i != size; ++i) {
            data.add(new DataModel());
            writeName(data.get(i));
            writeSurname(data.get(i));
            writePatronymic(data.get(i));
            writeCountry(data.get(i));
            writeRegion(data.get(i));
            writeCity(data.get(i));
            writeStreet(data.get(i));
            writeDOB(data.get(i));
            writeAge(data.get(i));
            writePostcode(data.get(i));
            writeApartment(data.get(i));
            writeHouse(data.get(i));
            writeTIN(data.get(i));
        }
        return data;
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, DataModel data) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(data.getName());
        row.createCell(1).setCellValue(data.getSurname());
        row.createCell(2).setCellValue(data.getSatronymic());
        row.createCell(3).setCellValue(data.getAge());
        row.createCell(4).setCellValue(data.getSex());
        row.createCell(5).setCellValue(data.getDOB().get(Calendar.DAY_OF_MONTH) + "-" + (data.getDOB().get(Calendar.MONTH) + 1) + "-" + data.getDOB().get(Calendar.YEAR));
        row.createCell(6).setCellValue(data.getTIN().toString());
        row.createCell(7).setCellValue(data.getPostcode());
        row.createCell(8).setCellValue(data.getCountry());
        row.createCell(9).setCellValue(data.getRegion());
        row.createCell(10).setCellValue(data.getCity());
        row.createCell(11).setCellValue(data.getStreet());
        row.createCell(12).setCellValue(data.getHouse());
        row.createCell(13).setCellValue(data.getApartment());
    }
}