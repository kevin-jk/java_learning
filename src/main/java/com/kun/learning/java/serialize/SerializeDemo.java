package com.kun.learning.java.serialize;


import java.io.*;

/**
 * Created by jrjiakun on 2018/10/30
 */
public class SerializeDemo {

    public static void main(String[]args) throws Exception{
       String filePath = "./src/main/resources/file/serialize/serializeFile.ser";
        Person person = new Person();
        person.setAddress("四川");
        person.setName("Kevin");
        person.setSex(true);
        person.setPhone("152000001");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
        oos.writeObject(person);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)));

        Person newPerson =(Person) ois.readObject();
        System.out.println(newPerson);
    }
}

// 会自动的生成serialVersionUID这个值。 当序列化和反序列化的serialVersionUID不同的时候，会报错
//自动生成serialVersionUID的规则是根据属性的相关信息生成。若增加了属性，就不能正常的反序列化（InvalidClassException）
// 因此需要手动的设置serialVersionUID这个值，确保可以在增加了字段的时候能够翻序列化

class Person implements Serializable{
    // 静态变量不被序列化，当类初始化的时候，加载类变量
    private static Integer AGE = 23;

    private String name;

    // 被transient标记的变量不会被序列化，反序列化的时候，其值为null
    transient  private String address;

    // phone 如果为敏感信息，则不能直接序列化，需要将其加密后再序列化，应该如何操作呢？
    // 将属性标记为transient, 然后重写writeObject 和 readObject方法。
    // 先调用defaultWriteObject方法，再在文件中追加额外需要序列化和反序列化的内容
    // ArrayList中的elementData的序列化就是如此，这样做可以只序列化有值的数组。
    transient  private String phone;

    private boolean sex;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static Integer getAGE() {
        return AGE;
    }

    public static void setAGE(Integer AGE) {
        Person.AGE = AGE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    // 序列化phone（transient标记内容）重新writeObject和readObject方法
    private void writeObject(ObjectOutputStream oos )throws Exception{
        oos.defaultWriteObject();
        String maxPhone = this.phone + "5";
        oos.writeObject(maxPhone);
    }

    private void readObject(ObjectInputStream ois) throws Exception{
        ois.defaultReadObject();
        String maxPhone = (String)ois.readObject();
        this.phone=maxPhone.substring(0,maxPhone.length()-1);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", age="+AGE+
                '}';
    }
}
