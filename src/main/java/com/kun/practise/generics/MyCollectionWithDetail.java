package com.kun.practise.generics;

/**
 * Created by jrjiakun on 2018/10/30
 *  * 传入泛型实参时：
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 *
 *如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends)
 如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super)
 如果既要存又要取，那么就不要使用任何通配符。
 */
public class MyCollectionWithDetail implements MyCollection<String> {
    @Override
    public void add(String data) {

    }

    @Override
    public String remove(String data) {
        return null;
    }
}
