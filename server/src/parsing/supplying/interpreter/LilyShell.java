package parsing.supplying.interpreter;

import communication.Report;
import communication.wrappers.AlertBag;
import communication.wrappers.ExecuteBag;
import communication.wrappers.QueryBag;
import entities.Junker;
import instructions.rotten.RawDecree;
import instructions.rotten.base.*;
import instructions.rotten.extended.*;
import parsing.Resolver;
import parsing.supplying.Invoker;

import java.io.*;

public final class LilyShell extends Shell {

  public LilyShell(Resolver controller, String filename, Invoker ctrl) {
    super(controller, filename, ctrl);
  }

  public Report read(ExecuteBag data) {
    Invoker ptr = CTRL;
    while (ptr instanceof Shell && ptr != null) {
      if (FILE_NAME.equals(((Shell) ptr).FILE_NAME)) return new Report(25,
          "Исполнение скрипта остановлено: обнаружена попытка создания рекурсии");
      ptr = ((Shell) ptr).CTRL;
    }
    String sep = System.getProperty("file.separator");
    String home = System.getProperty("user.dir");
  	try (
        FileInputStream descriptor = new FileInputStream(home + sep + "scripts" + sep + FILE_NAME);
        InputStreamReader rawReader = new InputStreamReader(descriptor);
        BufferedReader realfReader = new BufferedReader(rawReader);
  		) {
  	  // пока можем читать -- читаем строку
  	  while (realfReader.ready()) {
  	    String anotherLine = realfReader.readLine();
  	    RawDecree parsedCommand = parse(anotherLine, realfReader);
  	    if (parsedCommand == null) return new Report(404, "Не удалось найти команду");
  	    MAGIV.getInstBuilder().make(new QueryBag(null, parsedCommand), MAGIV.getFate());
      }
  	} catch (FileNotFoundException e) {
  	  return new Report(404, "Не удалось найти файл по укзанному имени");
  	} catch (IOException e) {
  		return new Report(4, "Не удалось получить доступ к потоку ввода для чтения файла");
  	}
  	return new Report(0, "Скрипт по имени " + FILE_NAME + " успешно исполнен");
  }

  @Override
  public RawDecree parse(String line, BufferedReader pReader) {
    // если строка пустая то и команду определить нельзя
    if (line == null || line.isEmpty()) return null;
    // иначе делим по пробелам
    String[] lineParts = line.split(" ");
    // проверяем смогли ли поделить
    if (lineParts == null || lineParts.length == 0)
      return null;
    else {
      // создаем определитель команд
      CommandDefiner cmdDeaf = new CommandDefiner(pReader);
      switch (lineParts.length) {
        // если длина массива одын, то команда без аргументов
        case 1: return cmdDeaf.define(0, lineParts);
        // если длина массива два, то это либо команда с одним аргументом,
        // либо с двумя
        case 2: return cmdDeaf.define(1, lineParts);
        // иначе какая-то неопределенная команда
        default: return null;
      }
    }
  }

  private class CommandDefiner {
    private final BufferedReader cReader;

    public CommandDefiner(BufferedReader cReader) {
      this.cReader = cReader;
    }

    public RawDecree define(int argc, String[] command_name) {
      switch (argc) {
        case 0: return defineWane(command_name[0]);
        case 1: return defineArgumented(command_name);
        default: return null;
      }
    }

    private RawDecree defineWane(String command_name) {
      // проверяем строку на пустоту, ради свича
      if (command_name == null || command_name.isEmpty()) return null;
      // тут все норм, свич юзает иквалз и все хорошо
      // солнышко светит, команды определяются
      switch (command_name) {
        case "help": return new RawHelp();
        case "info": return new RawInfo();
        case "clear": return new RawClear();
        case "sum_of_annual_turnover": return new RawSumOfAnnualTurnover();
        case "max_by_creation_date": return new RawMaxByDate();
        case "remove_lower": {
          ParamDefiner prmDeaf = new ParamDefiner(cReader);
          Junker element = prmDeaf.define();
          if (element != null)
            return new RawRemoveLower(element);
          else return null;
        }
        // тут солнышко скроется, команда не настроится
        default: return null;
      }
    }

    private RawDecree defineArgumented(String[] command_n_args) {
      String command_name = command_n_args[0]; // взяли название команды
      // взяли доп аргумент
      String argument = command_n_args[1];
      if (command_name == null || command_name.isEmpty()) return null;
      try {
        if (!junkedCommands.contains(command_name)) {
          switch (command_name) {
            case "remove_key": {
              Integer key = Integer.valueOf(argument); // переводим аргумент в число
              if (key == null) return null; // если вышло null, то и команда не определена
              return new RawRemoveKey(key); // иначе вернуть нормальную команду
            }
            case "execute_script":
              return new RawExecuteScript(argument);
            case "filter_contains_name":
              return new RawFilterContainsName(argument);
            default:
              return null;
          }
        } else {
          // начало обработки команды с элементом
          ParamDefiner prmDeaf = new ParamDefiner(cReader);
          Junker element = prmDeaf.define();
          if (element == null) return null;
          else {
            switch (command_name) {
              case "insert": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                return new RawInsert(key, element);
              }
              case "update": {
                Integer id = Integer.valueOf(argument);
                if (id == null) return null;
                return new RawUpdate(id, element);
              }
              case "remove_lower": return new RawRemoveLower(element);
              case "replace_if_lower": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                return new RawReplaceIfLower(key, element);
              }
              case "replace_if_greater": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                return new RawReplaceIfGreater(key, element);
              }
              default: return null;
            }
          }
        }
      } catch (NumberFormatException e) { return null; }
    }
  }

  private class ParamDefiner {
    private final BufferedReader pReader;
    public ParamDefiner(BufferedReader paramReader) {
      pReader = paramReader;
    }

    private boolean checkFilling(Object[] parameters) {
      boolean flag = true;
      for (Object param : parameters)
        if (param == null) {
          flag = false;
          return flag;
        }
      return flag;
    }

    public Junker define() {
      String name = null, fullname = null, type = null; // тип может быть null'ом
      Float annualTurnover = null;
      Integer employeesCount = null;
      Junker coordinates = null, address = null; // адрес может быть null'ом
      Object[] params = new Object[] { name, fullname, annualTurnover, employeesCount, coordinates };
      try {
        String smParameter = null;
        while (checkFilling(params)) {
          if (!pReader.ready()) return null;
          smParameter = pReader.readLine();
          if (smParameter == null || smParameter.isEmpty()) continue;
          String[] prmParts = smParameter.split(":");
          if (prmParts == null || prmParts.length == 0 || prmParts.length > 2) continue;
          String field = prmParts[0].trim();
          if (field == null || field.isEmpty()) continue;
          switch (field) {
            case "org.name": {
              if (prmParts.length == 1) continue;
              name = prmParts[1].trim();
              break;
            }
            case "org.fullname": {
              if (prmParts.length == 1) continue;
              fullname = prmParts[1].trim();
              break;
            }
            case "org.type": {
              if (prmParts.length == 1) continue;
              type = prmParts[1].trim();
              break;
            }
            case "org.annual-turnover": {
              if (prmParts.length == 1) continue;
              try { annualTurnover = Float.valueOf(prmParts[1].trim()); } catch (NumberFormatException e) { continue; }
              break;
            }
            case "org.employees-count": {
              if (prmParts.length == 1) continue;
              try { employeesCount = Integer.valueOf(prmParts[1].trim()); } catch (NumberFormatException e) { continue; }
              break;
            }
            case "org.address": {
              address = defAddress();
              break;
            }
            case "org.coordinates": {
              coordinates = defCoordinates();
              break;
            }
            default: continue;
          }
        }
      } catch (IOException e) {
        return null;
      }
      return new Junker(new long[]{employeesCount}, new double[]{annualTurnover}, new String[]{name, fullname, type}, new Junker[]{coordinates, address});
    }

    public Junker defAddress() {
      String zipCode = null;
      Junker town = null;
      try {
        if (!pReader.ready()) return null;
        String input = pReader.readLine();
        String arg = parseParam("addr.zipCode", input);
        if (!pReader.ready()) return new Junker(null, null, new String[]{zipCode}, null);
        town = defLocation();
      } catch (IOException e) {
        if (zipCode == null) return null;
        return new Junker(null, null, new String[]{zipCode}, null);
      }
      return new Junker(null, null, new String[]{zipCode}, new Junker[]{town});
    }

    public Junker defCoordinates() {
      Integer x = null; Float y = null;
      try {
        if (!pReader.ready()) return null;
        String input = pReader.readLine();
        String arg = parseParam("coord.x", input);
        x = Integer.valueOf(arg);
        input = pReader.readLine();
        arg = parseParam("coord.y", input);
        y = Float.valueOf(arg);
      } catch (NumberFormatException | IOException e) { return null; }
      return new Junker(new long[]{x}, new double[]{y}, null, null);
    }

    public Junker defLocation() {
      Long x = null, y = null; Double z = null;
      try {
        for (int i = 0; i < 3; ++i)
          if (!pReader.ready()) return null;
          else {
            String input = pReader.readLine();
            switch (i) {
              case 0: {
                String arg = parseParam("loc.x", input);
                x = Long.valueOf(arg);
                break;
              }
              case 1: {
                String arg = parseParam("loc.y", input);
                y = Long.valueOf(arg);
              }
              case 2: {
                String arg = parseParam("loc.z", input);
                z = Double.valueOf(arg);
              }
              default: return null;
            }
          }
      } catch (NumberFormatException | IOException e) { return null; }
      return new Junker(new long[]{x, y}, new double[]{z}, null, null);
    }

    private String parseParam(String goalp, String parsy) {
      if (parsy == null || parsy.isEmpty()) return null;
      String[] params = parsy.split(": ");
      if (params == null | params.length != 2) return null;
      if (params[0].equals(goalp))
        return params[1];
      else return null;
    }
  }
}
