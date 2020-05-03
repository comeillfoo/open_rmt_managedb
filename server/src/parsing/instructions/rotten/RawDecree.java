package parsing.instructions.rotten;

import parsing.instructions.Command;
import parsing.instructions.Decree;

import java.io.Serializable;

/**
 * Класс так называемых "сырых" комманд. Приходят от клиента,
 * не содержат готовых объектов, а лищь набор параметров.
 * Понятно, что содержат базовые данные о себе, то как название,
 * описание, синтаксис вызова. Не должны содержать ссылку на {@link parsing.customer.Receiver}
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Decree
 * @see Command
 */
public abstract class RawDecree extends Decree implements Serializable {}
