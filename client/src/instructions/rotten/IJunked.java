package instructions.rotten;

import entities.Junker;

/**
 * Обобщение для т.н. "сырых"
 * комманд, которые имеют дополнительный
 * (ключевой) параметр, который нужно учесть
 * при создании точной команды.
 * @param <D>
 */
public interface IJunked {
    Junker Params();
}
