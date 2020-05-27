package parsing.supplying.interpreter;

import com.sun.jmx.remote.internal.ArrayQueue;
import communication.Report;
import communication.wrappers.AlertBag;
import communication.wrappers.ExecuteBag;
import communication.wrappers.QueryBag;
import entities.Junker;
import instructions.concrete.extended.ExecuteScript;
import instructions.rotten.RawDecree;
import instructions.rotten.base.*;
import instructions.rotten.extended.*;
import parsing.Resolver;
import parsing.supplying.Invoker;
import parsing.supplying.interpreter.Shell;

import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayDeque;
import java.util.ArrayList;

public final class LilyShell extends Shell {

  private String executed = "";
  public LilyShell(Resolver controller, String filename, Invoker ctrl) {
    super(controller, filename, ctrl);
  }

  public LilyShell(Resolver controller, Invoker ctrl) {
    super(controller, ctrl);
  }

  public Report read(ExecuteBag data) {
    int capacity = ((ExecuteScript) data.Exec()).getScriptParts().size();
    ArrayList<String> tempScriptParts = ((ExecuteScript)data.Exec()).getScriptParts();
    ArrayDeque<String> scriptParticles = new ArrayDeque<>(capacity);
    for (int i = 0; i < capacity; i++) {
      scriptParticles.add(tempScriptParts.get(i));
    }
    Invoker ptr = CTRL;
    for(int i = 0; i < capacity; i = capacity - scriptParticles.size()) {
      RawDecree parsedCommand = parse(scriptParticles.poll(), scriptParticles);
      if (parsedCommand == null) {
        executed += "Не удалось разобрать строку "  + scriptParticles.peekFirst() + "\n";
      }
      MAGIV.getInstBuilder().make(new QueryBag(null, parsedCommand), MAGIV.getFate());
    }
    /*
    while (ptr instanceof Shell && ptr != null) {
      if (FILE_NAME.equals(((Shell) ptr).FILE_NAME)) return new Report(25,
          "Исполнение скрипта остановлено: обнаружена попытка создания рекурсии\n");
      ptr = ((Shell) ptr).CTRL;
    }
    String sep = System.getProperty("file.separator");
    String home = System.getProperty("user.dir");
    String exactWay = home + sep + "scripts" + sep +(FILE_NAME.contains(".pt")?FILE_NAME : FILE_NAME + ".pt");
  	try (
        FileInputStream descriptor = new FileInputStream(exactWay);
        InputStreamReader rawReader = new InputStreamReader(descriptor);
        BufferedReader realfReader = new BufferedReader(rawReader);
  		) {
  	  // пока можем читать -- читаем строку
  	  while (realfReader.ready()) {
  	    String anotherLine = realfReader.readLine();
  	    RawDecree parsedCommand = parse(anotherLine, realfReader);
  	    if (parsedCommand == null) executed += "Не удалось разобрать строку "  + anotherLine + "\n";
  	    MAGIV.getInstBuilder().make(new QueryBag(null, parsedCommand), MAGIV.getFate());
      }
  	} catch (FileNotFoundException e) {
  	  executed += "Не удалось найти файл по указанному имени\n";
  	} catch (IOException e) {
  		executed += "Не удалось получить доступ к потоку ввода для чтения файла\n";
  	}

     */
  	return new Report(0, "Скрипт по имени " + " обработан\nЗапись обработки файла:\n" + executed);
  }

  @Override
  public RawDecree parse(String line, ArrayDeque<String> scriptParts) {
    // если строка пустая то и команду определить нельзя
    if (line == null || line.isEmpty()) return null;
    // иначе делим по пробелам
    String[] lineParts = line.split(" ");
    System.out.println(lineParts[0]); //<----
    // проверяем смогли ли поделить
    if (lineParts == null || lineParts.length == 0)
      return null;
    else {
      // создаем определитель команд
      CommandDefiner cmdDeaf = new CommandDefiner(scriptParts);
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

  class CommandDefiner {
    private ArrayDeque<String> scriptParts;

    public CommandDefiner(ArrayDeque<String> scriptParts) {
      this.scriptParts = scriptParts;
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
        case "help": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawHelp();
        }
        case "info": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawInfo();
        }
        case "clear": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawClear();
        }
        case "show": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawShow();
        }
        case "sum_of_annual_turnover": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawSumOfAnnualTurnover();
        }
        case "max_by_creation_date": {
          executed += "Выполнена команда: " + command_name + "\n";
          return new RawMaxByDate();
        }
        case "remove_lower": {
          ParamDefiner prmDeaf = new ParamDefiner(scriptParts);
          Junker element = prmDeaf.define();
          if (element != null) {
            executed += "Выполнена команда: " + command_name + "\n";
            return new RawRemoveLower(element);
          } else return null;
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
              executed += "Выполнена команда: " + command_name + "\n";
              return new RawRemoveKey(key); // иначе вернуть нормальную команду
            }
            case "execute_script": {
              executed += "Выполнена команда: " + command_name + "\n";
              //return new RawExecuteScript();
            } case "filter_contains_name": {
              executed += "Выполнена команда: " + command_name + "\n";
              return new RawFilterContainsName(argument);
            } default:
              return null;
          }
        } else {
          // начало обработки команды с элементом
          ParamDefiner prmDeaf = new ParamDefiner(scriptParts);
          Junker element = prmDeaf.define();
          if (element == null) return null;
          else {
            switch (command_name) {
              case "insert": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                executed += "Выполнена команда: " + command_name + "\n";
                return new RawInsert(key, element);
              }
              case "update": {
                Integer id = Integer.valueOf(argument);
                if (id == null) return null;
                executed += "Выполнена команда: " + command_name + "\n";
                return new RawUpdate(id, element);
              }
              case "remove_lower": return new RawRemoveLower(element);
              case "replace_if_lower": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                executed += "Выполнена команда: " + command_name + "\n";
                return new RawReplaceIfLower(key, element);
              }
              case "replace_if_greater": {
                Integer key = Integer.valueOf(argument);
                if (key == null) return null;
                executed += "Выполнена команда: " + command_name + "\n";
                return new RawReplaceIfGreater(key, element);
              }
              default: return null;
            }
          }
        }
      } catch (NumberFormatException e) { return null; }
    }
  }

  class ParamDefiner {
    private final ArrayDeque<String> scriptParts;
    public ParamDefiner(ArrayDeque<String> scriptParts) {
      this.scriptParts = scriptParts;
    }

    private boolean checkFilling(Object[] parameters) {
      boolean flag = false;
      for (Object param : parameters) {
        System.out.println(param);
        if (param == null) {
          flag = true;
          return flag;
        }
      }
      return flag;
    }

    public Junker define() {
      String name = null, fullname = null, type = null; // тип может быть null'ом
      Float annualTurnover = null;
      Integer employeesCount = null;
      Junker coordinates = null, address = null; // адрес может быть null'ом
      Object[] params = new Object[] { name, fullname, annualTurnover, employeesCount, coordinates};
      boolean typeCheck =false, addressCheck = false;

      String smParameter = null;
      while (checkFilling(params) || !typeCheck || !addressCheck) {
          if (scriptParts.isEmpty()) break;
          smParameter = scriptParts.poll(); //<---------------------------------------------------------
          if (smParameter == null || smParameter.isEmpty()) continue;
          String[] prmParts = smParameter.split(":");
          if (prmParts == null || prmParts.length == 0 || prmParts.length > 2) continue;
          String field = prmParts[0].trim();
          if (field == null || field.isEmpty()) continue;
          switch (field) {
            case "org.name": {
              if (prmParts.length == 1) continue;
              name = prmParts[1].trim();
              params[0] = name;
              break;
            }
            case "org.fullname": {
              if (prmParts.length == 1) continue;
              fullname = prmParts[1].trim();
              params[1] = fullname;
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
              //TODO:Сервер падает если введены некоректные значения для обязательных полей
              params[2] = annualTurnover;
              break;
            }
            case "org.employees-count": {
              if (prmParts.length == 1) continue;
              try { employeesCount = Integer.valueOf(prmParts[1].trim()); } catch (NumberFormatException e) { continue; }
              params[3] = employeesCount;
              break;
            }
            case "org.address": {
              address = defAddress();
              break;
            }
            case "org.coordinates": {
              coordinates = defCoordinates();
              params[4] = coordinates;
              break;
            }
            default:
              if (!checkFilling(params)) {
                  typeCheck = true;
                  addressCheck = true;
              }
              continue;
          }
        }
      return new Junker(new long[]{employeesCount}, new double[]{annualTurnover}, new String[]{name, fullname, type}, new Junker[]{coordinates, address});
    }

    public Junker defAddress() {
      String zipCode = null;
      Junker town = null;
        //if (scriptParts.isEmpty()) return null;
        String input = scriptParts.poll();
        String arg = parseParam("addr.zipCode", input);
        if (scriptParts.isEmpty()) return new Junker(null, null, new String[]{zipCode}, null);
        town = defLocation();
//      } catch (IOException e) {
//        if (zipCode == null) return null;
//        return new Junker(null, null, new String[]{zipCode}, null);
//      }
      return new Junker(null, null, new String[]{zipCode}, new Junker[]{town});
    }

    public Junker defCoordinates() {
      Integer x = null; Float y = null;
      try {
        //if (scriptParts.isEmpty()) return null;
        String input = scriptParts.poll();
        String arg = parseParam("coord.x", input);
        x = Integer.valueOf(arg);
        input = scriptParts.poll();
        arg = parseParam("coord.y", input);
        y = Float.valueOf(arg);
      } catch (NumberFormatException ex) { return null; }
      return new Junker(new long[]{x}, new double[]{y}, null, null);
    }

    public Junker defLocation() {
      Long x = null, y = null; Double z = null;
      try {
        for (int i = 0; i < 3; ++i)
          if (scriptParts.isEmpty()) return null;
          else {
            String input = scriptParts.poll();
            switch (i) {
              case 0: {
                String arg = parseParam("loc.x", input);
                x = Long.valueOf(arg);
                break;
              }
              case 1: {
                String arg = parseParam("loc.y", input);
                y = Long.valueOf(arg);
                break;
              }
              case 2: {
                String arg = parseParam("loc.z", input);
                z = Double.valueOf(arg);
                break;
              }
              default: return null;
            }
          }
      } catch (NumberFormatException ex) { return null; }
      return new Junker(new long[]{x, y}, new double[]{z}, null, null);
    }

    private String parseParam(String goalp, String parsy) {
      if (parsy == null || parsy.isEmpty()) return null;
      String[] params = parsy.split(":");
      if (params == null | params.length != 2) return null;
      if (params[0].equals(goalp))
        return params[1];
      else return null;
    }
  }
}
