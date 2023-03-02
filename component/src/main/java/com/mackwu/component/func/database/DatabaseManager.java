package com.mackwu.component.func.database;

/**
 * @author MackWu
 * @since 2022/9/26 11:15
 */
public class DatabaseManager {


    /**
     * delete from TABLE_NAME where id=1
     * delete from TABLE_NAME where id between 13 and 25
     * delete from TABLE_NAME where in (2,3,4,5) https://stackoverflow.com/questions/14357964/how-to-delete-rows-in-sqlite-with-multiple-where-args
     *
     */
    public void delete(){}


    /**
     * update TABLE_NAME set status=1 where id=111
     * update TABLE_NAME set status=1 where id in (111,222,333)
     */
    public void update(){}

}
