package dyrax.klingon.Data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RoomConverter {

    @TypeConverter
    public static String fromIntList(ArrayList<Integer> ints) {
        return new Gson().toJson(ints);
    }

    @TypeConverter
    public static ArrayList<Integer> toIntList(String value) {
        return new Gson().fromJson(value, new TypeToken<ArrayList<Integer>>() {}.getType());
    }

    @TypeConverter
    public static String fromLongList(ArrayList<Long> longs) {
        return new Gson().toJson(longs);
    }

    @TypeConverter
    public static ArrayList<Long> toLongList(String value) {
        return new Gson().fromJson(value, new TypeToken<ArrayList<Long>>() {}.getType());
    }

    @TypeConverter
    public static String fromStringList(ArrayList<String> strings) {
        return new Gson().toJson(strings);
    }

    @TypeConverter
    public static ArrayList<String> toStringLists(String value) {
        return new Gson().fromJson(value, new TypeToken<ArrayList<String>>() {}.getType());
    }
}
