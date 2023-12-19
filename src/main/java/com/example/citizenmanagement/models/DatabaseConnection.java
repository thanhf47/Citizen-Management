package com.example.citizenmanagement.models;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Dialog;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
//        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "123456789";

        String url = "jdbc:sqlserver://LAPTOP-GKSBA9V1\\SQLEXPRESS:1433;databaseName=QUANLYDANCU;encrypt=true;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /******************************************************************************************/
    // Citizen Manager Section - Phần Đăng Nhập
    public ResultSet getCitizenManagerData(String tenDangNhap, String matKhau) {

        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "' AND MATKHAU = '" + matKhau +"'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet checkCitizenManagerUsernameExisted(String tenDangNhap) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet checkCitizenManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE HOTEN = N'" + hoTen + "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    public void updateCitizenManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String maKhau) {
        String query = "UPDATE NGUOIQUANLY SET MATKHAU = '" + maKhau + "' \n" +
                "WHERE HOTEN = N'" + hoTen+ "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCitizenManagerData(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        String query = "INSERT INTO NGUOIQUANLY(HOTEN, TENDANGNHAP, MATKHAU, SODIENTHOAI, VAITRO)\n" +
                        "VALUES (N'" + hoTen + "', '" + tenDangNhap + "', '" + matKhau + "', '" + soDienThoai + "', "+ Integer.toString(vaiTro)+ ")";

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**************************************************************************************/
    public ResultSet getNumberOfNhanhKhau() {
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfHoKhau(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MAHOKHAU) from HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfTamTru(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from TAMTRU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfTamVang(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from TAMVANG");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauNam(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauNu(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauDuoi3Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH < 3 AND YEAR(GETDATE()) - NAMSINH >= 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTu3Den10Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 3 AND YEAR(GETDATE()) - NAMSINH < 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu10Den18Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 10 AND YEAR(GETDATE()) - NAMSINH < 18");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu18Den60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 18 AND YEAR(GETDATE()) - NAMSINH < 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTren60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select YEAR(GETDATE())");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getHoKhauOfNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getHoKhauOfNam(int r){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU\n" +
                    "WHERE YEAR(NGAYTAO) =" + r);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getTamTruOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE MONTH(TUNGAY) =" + thang +"\n" +
                    "AND YEAR(TUNGAY) =" + nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;

    }

    public ResultSet getTamTruViLyDoHocTap(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Học tập%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }
    public ResultSet getTamTruViLyDoLamViec(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Làm việc%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamTruViLyDoSucKhoe(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Chăm sóc sức khỏe%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE MONTH(TUNGAY) =" +thang + "\n" +
                    "AND YEAR(TUNGAY) ="+ nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getTamVangViLyDoHocTap(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%Học tập%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoLamViec(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%Làm việc%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoSucKhoe(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%sức Khoẻ%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    /***********************************************************************************/
    //Nhân khẩu
    public int addNhanKhau (String hoTen, String CCCD, Date namSinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String noiThuongTru, String ngheNghiep, String ghiChu ){
        int thanhcong = 0;
        String querry = "insert into NHANKHAU (HOTEN, SOCANCUOC, NGAYSINH, GIOITINH, NOISINH, NGUYENQUAN, DANTOC, TONGIAO, QUOCTICH, NOITHUONGTRU, NGHENGHIEP, NGAYTAO, GHICHU )" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = connection.prepareStatement(querry);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setDate(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich);
            pre.setNString(10,noiThuongTru); pre.setNString(11,ngheNghiep);
            pre.setDate(12, Date.valueOf(LocalDate.now().toString())); pre.setNString(13,ghiChu);
            thanhcong = pre.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }
        return thanhcong;
    }
    public int addNhanKhauTamTru (String hoTen, String CCCD, Date namSinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String noiThuongTru, String ngheNghiep){
        int thanhcong = 0;
        String querry = "insert into NHANKHAU (HOTEN, SOCANCUOC, NGAYSINH, GIOITINH, NOISINH, NGUYENQUAN, DANTOC, TONGIAO, QUOCTICH, NOITHUONGTRU, NGHENGHIEP)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = connection.prepareStatement(querry);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setDate(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich);
            pre.setNString(10,noiThuongTru); pre.setNString(11,ngheNghiep);
            pre.setDate(12, Date.valueOf(LocalDate.now().toString()));
            thanhcong = pre.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }
        return thanhcong;
    }

    public int addTamtru(String hoTen, String CCCD, Date namSinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String noiThuongTru, String ngheNghiep, String maTamTru, Date ngayDen, Date ngayDi, String liDo ) {
        int thanhcong = 0;
        String que = "exec INSERT_TAMTRU ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try{
            PreparedStatement pre = connection.prepareStatement(que);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setDate(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich);
            pre.setNString(10,noiThuongTru); pre.setNString(11,ngheNghiep);
            pre.setString(12, maTamTru);
            pre.setDate(13, ngayDen); pre.setDate(14,ngayDi);
            pre.setNString(15,liDo);
            thanhcong = pre.executeUpdate();
        }catch(Exception e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }

        return thanhcong;
    }

    public int capnhatNhanKhau (String hoTen, String CCCD, String namSinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String ngheNghiep, String ghiChu ){
        int thanhcong = 0;
        String querry = "update NHANKHAU SET (HOTEN = ?, SOCANCUOC = ?, NGAYSINH = ?, GIOITINH = ?, NOISINH =? , NGUYENQUAN = ?, DANTOC = ? , TONGIAO = ?, QUOCTICH = ?, SOHOCHIEU =?, NOITHUONGTRU = ?, NGHENGHIEP  = ?,  GHICHU  = ? )" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = connection.prepareStatement(querry);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setString(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich); pre.setString(10,soHoChieu);
            pre.setNString(11,noiThuongTru); pre.setNString(12,ngheNghiep);
             pre.setNString(13,ghiChu);
            thanhcong = pre.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Lỗi câpj nhật khẩu");
            throw new RuntimeException(e);
        }
        return thanhcong;
    }
    // Nhân khâur

    public ResultSet truyvanlistNhanKhau( String socancuoc) {
        ResultSet resultSet = null;
        String que = "SELECT HOTEN, SOCANCUOC, NGAYSINH, GIOITINH, NOISINH, NGUYENQUAN, DANTOC, TONGIAO, QUOCTICH, NOITHUONGTRU, NGHENGHIEP, NGAYTAO, GHICHU " +
                "FROM NHANKHAU WHERE SOCANCUOC LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(que);
            preparedStatement.setString(1,  "%" + socancuoc + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet nhanKhau_timkiem(String string) {
        ResultSet resultSet = null;
        String querry = " select MANHANKHAU, SOCANCUOC, HOTEN, GIOITINH, NGAYSINH, NOITHUONGTRU from NHANKHAU where MANHANKHAU like ? or SOCANCUOC like ? or HOTEN like ? or GIOITINH like ? or NAMSINH like ? or NOITHUONGTRU like ?;";
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(querry);
            preparedstatement.setString(1, "%" + string + "%");
            preparedstatement.setString(2, "%" + string + "%");
            preparedstatement.setString(3, "%" + string + "%");
            preparedstatement.setString(4, "%" + string + "%");
            preparedstatement.setString(5, "%" + string + "%");
            preparedstatement.setString(6, "%" + string + "%");
            resultSet = preparedstatement.executeQuery();
        }
        catch(Exception e) {
            System.out.println("Lỗi tìm kiếm");
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    public ResultSet truyvan() {
        ResultSet resultSet = null;
        String querry = " select MANHANKHAU, SOCANCUOC, HOTEN, GIOITINH, NGAYSINH, NOITHUONGTRU from NHANKHAU;";
        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(querry);
        }
        catch(Exception e) {

        }
        return resultSet;
    }
    /***********************************************************************************/
    // Hộ khẩu

    public int addHoKhau(String ma_ch, String ngaythem, String diachi, String ghichu){
        if(!ma_ch.isEmpty() && !diachi.isEmpty() && !ngaythem.isEmpty()) {
            String query = "insert into HOKHAU (IDCHUHO, DIACHI, NGAYTAO, GHICHU) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, ma_ch);
                statement.setString(2, diachi);
                statement.setString(3, ngaythem);
                if(ghichu.isEmpty())
                    statement.setString(4,null);
                else
                    statement.setString(4, ghichu);

                statement.executeUpdate();
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        else
            return 0;
    }
    public ResultSet getDanhSachHoKhau(){
        String query = "select * from HOKHAU";
        ResultSet resultSet=null;
        try {
             resultSet = connection.createStatement().executeQuery(query);
        }catch (Exception e){
        }
        return resultSet;
    }
    public ResultSet timKiem(String dieukien){
        ResultSet resultSet=null;
        String query = "SELECT * FROM HOKHAU\n" +
                "WHERE MAHOKHAU LIKE ? OR IDCHUHO LIKE ? OR DIACHI LIKE ? OR NGAYTAO LIKE ? OR GHICHU LIKE ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + dieukien + "%");
            statement.setString(2, "%" + dieukien + "%");
            statement.setString(3, "%" + dieukien + "%");
            statement.setString(4, "%" + dieukien + "%");
            statement.setString(5, "%" + dieukien + "%");
            resultSet = statement.executeQuery();
        }catch (Exception e){
            System.out.println("timkiem");
        }
        return resultSet;
    }
    public int capNhatHoKhau(String idHoKhau, String maChuHo, String diaChi, String ghiChu){
        ResultSet resultSet=null;
        try {
            String capnhat = "update HOKHAU set IDCHUHO=?, DIACHI=?, GHICHU=? where MAHOKHAU=?";
            PreparedStatement preparedStatement = connection.prepareStatement(capnhat);
            preparedStatement.setString(1,maChuHo);
            preparedStatement.setString(2,diaChi);

            if(ghiChu.isEmpty())
                preparedStatement.setString(3,null);
            else
                preparedStatement.setString(3, ghiChu);
            preparedStatement.setString(4,idHoKhau);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("loi o capnhat !!!!!!!!!!!!!");
            return 0;
        }
    }
    public int xoaHoKhau(String maHoKhau) {
        int res = 0;
        String query = "DELETE HOKHAU\n" +
                "WHERE MAHOKHAU = " + maHoKhau;
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            res = 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


    /***************************************************************************/
    // Quản lý thu phí
    public ResultSet getDanhSachDongPhi() {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU)\n" +
                "FROM HOKHAU HK INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI";
        Statement statement;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
        }
        return resultSet;
    }

    public ResultSet danhsachdongphi_timKiem(String condition) {
        String query = "SELECT HK.MAHOKHAU\n" +
                "FROM HOKHAU HK INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "INNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE HK.MAHOKHAU LIKE '%" + condition + "%' \n" +
                "\tOR NK.HOTEN LIKE N'%" + condition + "%'\n" +
                "\tOR DIACHI LIKE N'%" + condition+ "%'\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void themKhoanThuPhi(String tenKhoanThu, int batBuoc, int soTienCanDong, LocalDate ngayTao, String moTa) {
        String query = "INSERT INTO LOAIPHI(TEN, BATBUOC, SOTIENTRENMOTNGUOI, NGAYTAO, MOTA)\n" +
                "VALUES (N'" + tenKhoanThu + "', " + batBuoc + ", "+ soTienCanDong + ", '" + ngayTao.toString() + "', N'" + moTa +"')";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int layMaKhoanThu(String tenKhoanThu,int batBuoc, int soTienCanDong, LocalDate ngayTao, String moTa) {
        int maKhoanThu = -1;

        String query = "SELECT MAKHOANTHU\n" +
                "FROM LOAIPHI\n" +
                "WHERE TEN = N'" + tenKhoanThu + "' AND BATBUOC = " + batBuoc +
                " AND SOTIENTRENMOTNGUOI = " + soTienCanDong + "AND NGAYTAO = '" + ngayTao.toString() + "' AND MOTA = N'" + moTa +"'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                maKhoanThu = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maKhoanThu;
    }
    public void themDanhSachThuPhi(int maHoKhau, int maKhoanThu, int soTienCanDong, int trangThai) {
        String query = "INSERT INTO DONGGOP(MAHOKHAU, MAKHOANTHU, SOTIENCANDONG, TRANGTHAI)\n" +
                "VALUES (" + maHoKhau + ", " + maKhoanThu + ", " + soTienCanDong + ", " + trangThai + ")";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getDanhSachKhoanThu() {
        String query = "SELECT * FROM LOAIPHI";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet danhSachKhoanThu_timKiem(String condition) {
        String query = "SELECT * FROM LOAIPHI\n" +
                "WHERE MAKHOANTHU LIKE '%" + condition + "%' OR TEN LIKE N'%" + condition + "%'";
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


}


