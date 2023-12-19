package com.example.citizenmanagement.models;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Dialog;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "040703";

        String url = "jdbc:sqlserver://MAIN-CHARACTER\\THANH_NGUYEN:1433;databaseName=" + dbName +
                ";encrypt=true;integratedSecurity=false;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private ResultSet executeQuery(String query) {
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
    private void executeUpdate(String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /******************************************************************************************/
    // Citizen Manager Section - Phần Đăng Nhập
    public ResultSet getCitizenManagerData(String tenDangNhap, String matKhau) {

        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "' AND MATKHAU = '" + matKhau +"'";
        return executeQuery(query);
    }

    public ResultSet checkCitizenManagerUsernameExisted(String tenDangNhap) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "'";
        return executeQuery(query);
    }

    public ResultSet checkCitizenManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE HOTEN = N'" + hoTen + "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        return executeQuery(query);
    }
    public void updateCitizenManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String maKhau) {
        String query = "UPDATE NGUOIQUANLY SET MATKHAU = '" + maKhau + "' \n" +
                "WHERE HOTEN = N'" + hoTen+ "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        executeUpdate(query);
    }
    public void setCitizenManagerData(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        String query = "INSERT INTO NGUOIQUANLY(HOTEN, TENDANGNHAP, MATKHAU, SODIENTHOAI, VAITRO)\n" +
                        "VALUES (N'" + hoTen + "', '" + tenDangNhap + "', '" + matKhau + "', '" + soDienThoai + "', "+ Integer.toString(vaiTro)+ ")";

        executeUpdate(query);
    }

    /**************************************************************************************/
    // trang chủ - thống kê quản lý dân cư
    public ResultSet getNumberOfTamTru(int nam){
        String query = "SELECT COUNT(MAGIAYTAMTRU) FROM TAMTRU " +
                "WHERE " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }
    public ResultSet getNumberOfTamVang(int nam){

        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG WHERE " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }
    public ResultSet getNumberOfNhanhKhau() {
        String query = "select count(MANHANKHAU) from NHANKHAU";
        return executeQuery(query);
    }

    public ResultSet getNumberOfHoKhau(){
        String query = "select count(MAHOKHAU) from HOKHAU";
        return executeQuery(query);
    }

    public ResultSet getNumberOfTamTru(){
        String query = "SELECT COUNT(MAGIAYTAMTRU) FROM TAMTRU WHERE YEAR(GETDATE()) BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY) ";
        return executeQuery(query);
    }
    public ResultSet getNumberOfTamVang(){
        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG WHERE YEAR(GETDATE()) BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY) ";
        return executeQuery(query);
    }

    public ResultSet getNumberOfNhanKhauNam(){
        String query = "select count(MANHANKHAU) from NHANKHAU where GIOITINH = 1";
        return executeQuery(query);
    }
    public ResultSet getNumberOfNhanKhauNu(){
        String query = "select count(MANHANKHAU) from NHANKHAU where GIOITINH = 0";
        return executeQuery(query);
    }

    public ResultSet getNumberOfNhanKhauDuoi3Tuoi(){
        String query = "select count(MANHANKHAU) \n" +
                "from NHANKHAU\n" +
                "where YEAR(GETDATE()) - YEAR(NGAYSINH) < 3 AND YEAR(GETDATE()) - YEAR(NGAYSINH) >= 0";
        return executeQuery(query);
    }
    public ResultSet getNumberOfNhanKhauTu3Den10Tuoi(){
        String query = "select count(MANHANKHAU) \n" +
                "from NHANKHAU\n" +
                "where YEAR(GETDATE()) - YEAR(NGAYSINH) >= 3 AND YEAR(GETDATE()) - YEAR(NGAYSINH) < 10";
        return executeQuery(query);
    }

    public ResultSet getNumberOfNhanKhauTu10Den18Tuoi(){
        String query = "select count(MANHANKHAU) \n" +
                "from NHANKHAU\n" +
                "where YEAR(GETDATE()) - YEAR(NGAYSINH) >= 10 AND YEAR(GETDATE()) - YEAR(NGAYSINH) < 18";
        return executeQuery(query);
    }

    public ResultSet getNumberOfNhanKhauTu18Den60Tuoi(){
        String query = "select count(MANHANKHAU) \n" +
                "from NHANKHAU\n" +
                "where YEAR(GETDATE()) - YEAR(NGAYSINH) >= 18 AND YEAR(GETDATE()) - YEAR(NGAYSINH) < 60";
        return executeQuery(query);
    }
    public ResultSet getNumberOfNhanKhauTren60Tuoi(){
        String query = "select count(MANHANKHAU) \n" +
                "from NHANKHAU\n" +
                "where YEAR(GETDATE()) - YEAR(NGAYSINH) >= 60";
        return executeQuery(query);
    }
    public ResultSet getNamHienTai(){
        String query = "select YEAR(GETDATE())";
        return executeQuery(query);
    }
    public ResultSet getHoKhauOfNamHienTai(){
        String query = "SELECT COUNT(MAHOKHAU)\n" +
                "FROM HOKHAU";
        return executeQuery(query);
    }

    public ResultSet getHoKhauOfNam(int nam){
        String query = "SELECT COUNT(MAHOKHAU)\n" +
                "FROM HOKHAU\n" +
                "WHERE " + nam + " > YEAR(NGAYTAO)";
        return executeQuery(query);
    }

    public ResultSet getTamTruOfThangVaNam(int thang,int nam){
        String query = "SELECT COUNT(MAGIAYTAMTRU)\n" +
                "FROM TAMTRU\n" +
                "WHERE " + thang + " BETWEEN MONTH(TUNGAY) AND MONTH(DENNGAY)" + "\n" +
                "AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    public ResultSet getTamTruViLyDoHocTap(int nam){
        String query = "SELECT COUNT(MAGIAYTAMTRU)\n" +
                "FROM TAMTRU\n" +
                "WHERE LYDO LIKE N'%Học tập%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
       return executeQuery(query);
    }
    public ResultSet getTamTruViLyDoLamViec(int nam){
        String query = "SELECT COUNT(MAGIAYTAMTRU)\n" +
                "FROM TAMTRU\n" +
                "WHERE LYDO LIKE N'%Làm việc%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    public ResultSet getTamTruViLyDoSucKhoe(int nam){
        String query = "SELECT COUNT(MAGIAYTAMTRU)\n" +
                "FROM TAMTRU\n" +
                "WHERE LYDO LIKE N'%sức khỏe%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    public ResultSet getTamVangOfThangVaNam(int thang,int nam){
        String query = "SELECT COUNT(MAGIAYTAMVANG)\n" +
                "FROM TAMVANG\n" +
                "WHERE " + thang + " BETWEEN MONTH(TUNGAY) AND MONTH(DENNGAY)" + "\n" +
                "AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    public ResultSet getTamVangViLyDoHocTap(int nam){
        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG WHERE LYDO LIKE N'%Học tập%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";

        return executeQuery(query);
    }

    public ResultSet getTamVangViLyDoLamViec(int nam){
        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG   WHERE LYDO LIKE N'%Làm việc%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    public ResultSet getTamVangViLyDoSucKhoe(int nam){
        String query = "SELECT COUNT(MAGIAYTAMVANG) FROM TAMVANG  WHERE LYDO LIKE N'%sức Khoẻ%' AND " + nam + " BETWEEN YEAR(TUNGAY) AND YEAR(DENNGAY)";
        return executeQuery(query);
    }

    /***********************************************************************************/
    //Nhân khẩu
    public int addNhanKhau (String hoTen, String CCCD, String ngaySinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String noiThuongTru, String ngheNghiep, String ngayTao, String ghiChu ){
        int thanhcong = 0;
        String querry = "insert into NHANKHAU (HOTEN, SOCANCUOC, YEAR(NGAYSINH), GIOITINH, NOISINH, NGUYENQUAN, DANTOC, TONGIAO, QUOCTICH, NOITHUONGTRU, NGHENGHIEP, NGAYTAO, GHICHU )" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = connection.prepareStatement(querry);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setString(3,ngaySinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich);
            pre.setNString(10,noiThuongTru); pre.setNString(11,ngheNghiep);
            pre.setDate(12, Date.valueOf(ngayTao)); pre.setNString(13,ghiChu);
            thanhcong = pre.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }
        return thanhcong;
    }

    public int addTamtru(String hoTen, String CCCD, String ngaySinh, int gioiTinh, String noiSinh, String nguyenQuan, String danToc, String tonGiao, String quocTich, String noiThuongTru, String ngheNghiep, String sdt, Date ngayDen, Date ngayDi, String liDo ) {
        int thanhcong = 0;
        String que = "ínsert to TAMTRU()" +
                "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pre = connection.prepareStatement(que);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setString(3, ngaySinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich);
            pre.setNString(10,noiThuongTru); pre.setNString(11,ngheNghiep);
            pre.setString(12, sdt);
            pre.setDate(13, ngayDen); pre.setDate(14,ngayDi);
            pre.setNString(15,liDo);
            thanhcong = pre.executeUpdate();
        }catch(Exception e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }

        return thanhcong;
    }
    public ResultSet nhanKhau_timkiem(String string) {
        ResultSet resultSet = null;
        String querry = " select SOCANCUOC, HOTEN, GIOITINH, YEAR(NGAYSINH), NOITHUONGTRU from NHANKHAU where SOCANCUOC like ? or HOTEN like ? or GIOITINH like ? or YEAR(NGAYSINH) like ? or NOITHUONGTRU like ?;";
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(querry);
            preparedstatement.setString(1, "%" + string + "%");
            preparedstatement.setString(2, "%" + string + "%");
            preparedstatement.setString(3, "%" + string + "%");
            preparedstatement.setString(4, "%" + string + "%");
            preparedstatement.setString(5, "%" + string + "%");
            resultSet = preparedstatement.executeQuery();
        }
        catch(Exception e) {
            System.out.println("Lỗi tìm kiếm");
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    public ResultSet truyvan() {

        String query = " select SOCANCUOC, HOTEN, GIOITINH, YEAR(NGAYSINH), NOITHUONGTRU from NHANKHAU;";
        return executeQuery(query);
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
        return executeQuery(query);
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
        return executeQuery(query);
    }

    public ResultSet danhsachdongphi_timKiem(String condition) {
        String query = "SELECT HK.MAHOKHAU\n" +
                "FROM HOKHAU HK INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "INNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE HK.MAHOKHAU LIKE '%" + condition + "%' \n" +
                "\tOR NK.HOTEN LIKE N'%" + condition + "%'\n" +
                "\tOR DIACHI LIKE N'%" + condition+ "%'\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI";
        return executeQuery(query);
    }
    public void themKhoanThuPhi(String tenKhoanThu, int batBuoc, long soTienCanDong, LocalDate ngayTao, String moTa) {
        String query = "INSERT INTO LOAIPHI(TEN, BATBUOC, SOTIENTRENMOTNGUOI, NGAYTAO, MOTA)\n" +
                "VALUES (N'" + tenKhoanThu + "', " + batBuoc + ", "+ soTienCanDong + ", '" + ngayTao.toString() + "', N'" + moTa +"')";
        executeUpdate(query);
    }
    public int layMaKhoanThu(String tenKhoanThu,int batBuoc, long soTienCanDong, LocalDate ngayTao, String moTa) {
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
    public void themDanhSachThuPhi(int maHoKhau, int maKhoanThu, long soTienCanDong, int trangThai) {
        String query = "INSERT INTO DONGGOP(MAHOKHAU, MAKHOANTHU, SOTIENCANDONG, TRANGTHAI)\n" +
                "VALUES (" + maHoKhau + ", " + maKhoanThu + ", " + soTienCanDong + ", " + trangThai + ")";
        executeUpdate(query);
    }

    public ResultSet getDanhSachKhoanThu() {
        String query = "SELECT * FROM LOAIPHI";
        return executeQuery(query);
    }

    public ResultSet getKhoanThuPhi(int maKhoanThu) {
        String query = "SELECT * FROM LOAIPHI\n" +
                "WHERE MAKHOANTHU LIKE '" + maKhoanThu + "'";
        return executeQuery(query);
    }
    public ResultSet danhSachKhoanThu_timKiem(String condition) {
        String query = "SELECT * FROM LOAIPHI\n" +
                "WHERE MAKHOANTHU LIKE '%" + condition + "%' OR TEN LIKE N'%" + condition + "%'";
        return executeQuery(query);
    }

    public int getSoLuongHoDaDongPhi(int maKhoanThu) {

        int res = 0;

        String query = "SELECT COUNT(MAHOKHAU) FROM DONGGOP\n" +
                "WHERE MAKHOANTHU LIKE '" + maKhoanThu + "' AND TRANGTHAI = 1";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getSoLuongHoChuaDongPhi(int maKhoanThu) {

        int res = 0;

        String query = "SELECT COUNT(MAHOKHAU) FROM DONGGOP\n" +
                "WHERE MAKHOANTHU LIKE '" + maKhoanThu + "' AND TRANGTHAI = 0";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getSoLuongHoDongPhi(int maKhoanThu) {

        int res = 0;

        String query = "SELECT COUNT(MAHOKHAU) FROM DONGGOP\n" +
                "WHERE MAKHOANTHU = " + maKhoanThu;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void deleteKhoanThuPhi(int maKhoanThu) {
        String query1 = "DELETE DONGGOP\n" +
                "WHERE MAKHOANTHU LIKE '" + maKhoanThu + "'";
        String query2 = "DELETE LOAIPHI\n" +
                "WHERE MAKHOANTHU LIKE '" + maKhoanThu + "'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getDanhSachChuaDongPhi(int maKhoanThu) {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU), DONGGOP.SOTIENCANDONG\n" +
                "FROM DONGGOP INNER JOIN HOKHAU HK ON DONGGOP.MAHOKHAU = HK.MAHOKHAU\n" +
                "INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE DONGGOP.MAKHOANTHU = " + maKhoanThu + " AND TRANGTHAI = 0\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, DONGGOP.SOTIENCANDONG";
        return executeQuery(query);
    }

    public ResultSet danhSachChuaDongPhi_timKiem(int maKhoanThu, String condition) {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU), DONGGOP.SOTIENCANDONG\n" +
                "FROM DONGGOP INNER JOIN HOKHAU HK ON DONGGOP.MAHOKHAU = HK.MAHOKHAU\n" +
                "INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE DONGGOP.MAKHOANTHU = " + maKhoanThu + " AND TRANGTHAI = 0\n" +
                "\tAND (HK.MAHOKHAU LIKE '%" + condition + "%' OR NK.HOTEN LIKE '%" + condition + "%' OR HK.DIACHI LIKE '%" + condition + "%')\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, DONGGOP.SOTIENCANDONG";
        return executeQuery(query);
    }

    public void updateNopPhi(int maHoKhau, int maKhoanThu, String soTien) {
        String query = "UPDATE DONGGOP\n" +
                "SET TRANGTHAI = 1, NGAYDONG = GETDATE(), SOTIENDADONG = " + soTien + "\n" +
                "WHERE MAHOKHAU = " + maHoKhau + " AND MAKHOANTHU = " + maKhoanThu;
        executeUpdate(query);
    }
    public String getNgayNopPhi(int maHoKhau, int maKhoanThu) {
        String query = "SELECT NGAYDONG FROM DONGGOP\n" +
                "WHERE MAHOKHAU = " + maHoKhau + " AND MAKHOANTHU = " + maKhoanThu;
        String ngayNopPhi = "";
        ResultSet resultSet = executeQuery(query);
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                ngayNopPhi = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ngayNopPhi;
    }

    public ResultSet danhSachDaDongPhi_timKiem(int maKhoanThu, String condition) {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU), DG.SOTIENDADONG\n" +
                "FROM DONGGOP DG INNER JOIN HOKHAU HK ON DG.MAHOKHAU = HK.MAHOKHAU\n" +
                "INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE DG.MAKHOANTHU = " + maKhoanThu + " AND TRANGTHAI = 1\n" +
                "\tAND (HK.MAHOKHAU LIKE '%" + condition + "%' OR NK.HOTEN LIKE '%" + condition + "%' OR HK.DIACHI LIKE '%" + condition + "%')\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, DG.SOTIENDADONG";
        return executeQuery(query);
    }

    public ResultSet getDanhSachDaDongPhi(int maKhoanThu) {
        String query = "SELECT HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, COUNT(TV.MANHANKHAU), DG.SOTIENDADONG\n" +
                "FROM DONGGOP DG INNER JOIN HOKHAU HK ON DG.MAHOKHAU = HK.MAHOKHAU\n" +
                "INNER JOIN NHANKHAU NK ON HK.IDCHUHO = NK.MANHANKHAU\n" +
                "\tINNER JOIN THANHVIENCUAHO TV ON HK.MAHOKHAU = TV.MAHOKHAU\n" +
                "WHERE DG.MAKHOANTHU = " + maKhoanThu + " AND TRANGTHAI = 1\n" +
                "GROUP BY HK.MAHOKHAU, NK.HOTEN, HK.DIACHI, DG.SOTIENDADONG";
        return executeQuery(query);
    }

    public ResultSet getDSNguoiChet() {
        String query = "select MANHANKHAU, SOCANCUOC, HOTEN, GIOITINH, NGAYSINH, NOITHUONGTRU\n" +
                "from NHANKHAU INNER JOIN KHAITU ON NHANKHAU.MANHANKHAU = KHAITU.MANHANKHAUNGUOICHET";

        return executeQuery(query);
    }

    public ResultSet deadNhanKhau_timkiem(String condition) {
        String query = "select MANHANKHAU, SOCANCUOC, HOTEN, GIOITINH, NGAYSINH, NOITHUONGTRU\n" +
                "from NHANKHAU INNER JOIN KHAITU ON NHANKHAU.MANHANKHAU = KHAITU.MANHANKHAUNGUOICHET\n" +
                "WHERE MANHANKHAU LIKE '%" + condition + "%' OR SOCANCUOC LIKE '%" + condition + "%' OR HOTEN LIKE '%" + condition + "%'";

        return executeQuery(query);
    }
    public ResultSet getThongTinKhaiTu(String maNhanKhauNguoiChet) {
        String query = "SELECT KT.MAGIAYKHAITU, NK1.MANHANKHAU, NK1.HOTEN, NK2.MANHANKHAU, NK2.HOTEN, NK2.SOCANCUOC, NK2.NGAYSINH, NK2.GIOITINH, NK2.DANTOC, NK2.QUOCTICH,\n" +
                "\tNK2.NGUYENQUAN, NK2.NOITHUONGTRU, KT.NGAYKHAI, KT.NGAYCHET, KT.LYDOCHET\n" +
                "FROM KHAITU KT INNER JOIN NHANKHAU NK1 ON KT.MANHANKHAUNGUOIKHAI = NK1.MANHANKHAU\n" +
                "\tINNER JOIN NHANKHAU NK2 ON KT.MANHANKHAUNGUOICHET = NK2.MANHANKHAU\n" +
                "WHERE KT.MANHANKHAUNGUOICHET = " + maNhanKhauNguoiChet;
        return executeQuery(query);
    }

    public void updateThongTinKhaiTu(String maGiayKhaiTu, String ngayKhai, String ngayChet, String lyDo) {
        String query = "UPDATE KHAITU\n" +
                "SET NGAYKHAI = '" + ngayKhai + "', NGAYCHET = '" + ngayChet + "', LYDOCHET = N'" + lyDo + "'\n" +
                "WHERE MAGIAYKHAITU = " + maGiayKhaiTu;
        executeUpdate(query);
    }
    /***************************************************************************/


}


