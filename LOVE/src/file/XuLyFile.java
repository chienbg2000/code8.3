package file;

import java.io.*;
import java.util.List;
public class XuLyFile {
    /*
    Bản quyền thuộc về : Nguyễn Văn Chiến 1810A01. Vui lòng ghi nguồn khi sử dụng :)))
    */
     private XuLyFile() {
     }

    /**
     *<p> Phương thức này truyền vào:
      <p>     - Danh sách cần lưu.
      <p>     - Địa chỉ truyền vào.
     * Các phần tử trong danh sách cần phải được implements Serializable.
     * <p>Nếu file chưa tồn tại nó sẽ tạo 1 file với địa chỉ đã cho.
     * <p>Phương thức này ghi đè lên file dưới dạng nhị phân.
     * @param danhSach (ArrayList,LinkList,...)
     * @param diaChi   (VD: C:Desktop\\ListObject.dat)
     */
    public static void writeFile(List danhSach, String diaChi){
        File file = new File(diaChi);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("CO LOI XAY RA KHI MO FILE.");
            }
        }
        FileOutputStream FOut = null;
        ObjectOutputStream OOut = null;
        try {
            FOut = new FileOutputStream(file);
            OOut = new ObjectOutputStream(FOut);
            OOut.writeObject(danhSach);
            System.out.println("GHI FILE THANH CONG");
        } catch (Exception e) {
            System.err.println("CO LOI XAY RA KHI GHI FILE.");
        }
        finally {
            try {
                FOut.close();
                OOut.close();

            } catch (IOException e) {
                System.err.println("CO LOI XAY RA KHI DONG FILE.");
            }
        }
    }

    /**
     * Phương thức này truyền vào địa chỉ file cần đọc.
     * Phương thức này trả về 1 Object với dữ liệu được đọc từ file.
     * Cần phải ép kiểu Object trả về đến đối tượng bạn cần sử dụng. VD:(ArrayList<>)readFile;
     * @param diaChi
     * @return Object
     */
    public static Object readFile(String diaChi){
        FileInputStream FIn = null;
        ObjectInputStream OIn = null;
        Object read = null;
        try {
            FIn = new FileInputStream(new File(diaChi));
            OIn = new ObjectInputStream(FIn);
            read = OIn.readObject();
            return read;
        } catch (Exception e) {
            System.err.println("CO LOI XAY RA KHI DOC FILE.");
        }
        finally {if (read != null)
            System.out.println("DOC FILE THANH CONG!");
            else System.out.println("DOC FILE THAT BAI");
            try {
                FIn.close();
                OIn.close();
            } catch (IOException e) {
                System.err.println("CO LOI XAY RA KHI DONG FIlE.");
            }
        }

        return null;
    }


}
