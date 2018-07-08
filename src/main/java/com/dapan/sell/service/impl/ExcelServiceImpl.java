package com.dapan.sell.service.impl;

import com.dapan.sell.dataobject.WxProduct;
import com.dapan.sell.enums.ActivityStatusEnum;
import com.dapan.sell.exception.ExcelException;
import com.dapan.sell.repository.WxAllianceRepository;
import com.dapan.sell.service.ExcelService;
import com.dapan.sell.utils.EnumUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private WxAllianceRepository repository;

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {

        boolean notNull = false;

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ExcelException("上传文件格式不正确");
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        InputStream inputStream = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            wb = new XSSFWorkbook(inputStream);
        }

        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }

        ArrayList<WxProduct> wxProductArrayList = new ArrayList<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row cells = sheet.getRow(i);
            if (cells == null) {
                continue;
            }

            WxProduct wxProduct = new WxProduct();

            String productId = cells.getCell(0).getStringCellValue();
            wxProduct.setProductId(productId);
            String productName = cells.getCell(1).getStringCellValue();
            wxProduct.setProductName(productName);
            String productPic = cells.getCell(2).getStringCellValue();
            wxProduct.setProductPic(productPic);
            String productDetailUrl = cells.getCell(3).getStringCellValue();
            wxProduct.setProductDetailUrl(productDetailUrl);
            String storeName = cells.getCell(4).getStringCellValue();
            wxProduct.setStoreName(storeName);
            BigDecimal productPrice = getBigDecimal(cells.getCell(5).getStringCellValue());
            wxProduct.setProductPrice(productPrice);
            int productSaleCount = Integer.parseInt(cells.getCell(6).getStringCellValue());
            wxProduct.setProductSaleCount(productSaleCount);
            BigDecimal commonIncomeRatio = getBigDecimal(cells.getCell(7).getStringCellValue());
            wxProduct.setCommonIncomeRatio(commonIncomeRatio);
            BigDecimal commonCommission = getBigDecimal(cells.getCell(8).getStringCellValue());
            wxProduct.setCommonCommission(commonCommission);

            int activityStatus = EnumUtil.getByMessage(cells.getCell(9).getStringCellValue(), ActivityStatusEnum.class).getCode();
            wxProduct.setActivityStatus(activityStatus);
            BigDecimal activityIncomeRatio = getBigDecimal(getStringCellValue(cells.getCell(10)));
            wxProduct.setActivityIncomeRatio(activityIncomeRatio);
            BigDecimal activityCommission = getBigDecimal(getStringCellValue(cells.getCell(11)));
            wxProduct.setActivityCommission(activityCommission);


            try {
                Date activityStartTime = parse(cells.getCell(12).getStringCellValue());
                wxProduct.setActivityStartTime(activityStartTime);

                Date activityEndTime = parse(cells.getCell(13).getStringCellValue());
                wxProduct.setActivityEndTime(activityEndTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            String sellerWW = cells.getCell(14).getStringCellValue();
            wxProduct.setSellerWW(sellerWW);
            String taobaoSpreaderShortLink = cells.getCell(15).getStringCellValue();
            wxProduct.setTaobaoSpreaderShortLink(taobaoSpreaderShortLink);
            String taobaoSpreaderLink = cells.getCell(16).getStringCellValue();
            wxProduct.setTaobaoSpreaderLink(taobaoSpreaderLink);
            String taoToken = cells.getCell(17).getStringCellValue();
            wxProduct.setTaoToken(taoToken);
            int couponAmount = Integer.parseInt(cells.getCell(18).getStringCellValue());
            wxProduct.setCouponAmount(couponAmount);
            int couponBalance = Integer.parseInt(cells.getCell(19).getStringCellValue());
            wxProduct.setCouponBalance(couponBalance);

                wxProduct.setCouponDenomination(cells.getCell(20).getStringCellValue());
            try {
                Date couponStart = parse(cells.getCell(21).getStringCellValue());
                wxProduct.setCouponStart(couponStart);

                Date couponEnd = parse(cells.getCell(22).getStringCellValue());
                wxProduct.setCouponEnd(couponEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            wxProduct.setCouponLink(getStringCellValue(cells.getCell(23)));
            wxProduct.setCouponToken(getStringCellValue(cells.getCell(24)));
            String couponShortLink = getStringCellValue(cells.getCell(25));
            wxProduct.setCouponShortLink(couponShortLink);

            wxProductArrayList.add(wxProduct);

            repository.saveAll(wxProductArrayList);
//            for (Cell cell : cells) {
//                System.out.print(cell.getStringCellValue() + "\t");
//                wxProduct.setProductId(cell.getStringCellValue());
//            }

        }


        return notNull;
    }

    private String getStringCellValue(Cell cell) {
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private BigDecimal getBigDecimal(String value) {
        if ("0".equals(value) || StringUtils.isEmpty(value)) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(value);
        }
    }


    private SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date parse(String src) throws ParseException {

        if (StringUtils.isEmpty(src)) {
            return null;
        }
        if ("yyyy-MM-dd".length() == src.length()) {
            return myFmt1.parse(src);
        }

        if ("1970-01-01 08:00:00".equals(src)) {
            return null;
        }

        if ("yyyy-MM-dd HH:mm:ss".length() == src.length()) {
            return myFmt2.parse(src);
        }

        return null;

    }
}
