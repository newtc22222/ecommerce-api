package com.hcmute.ecom.enums;

/**
 * <ul>
 *     <li>PENDING - Đơn hàng ở trạng thái đặt hàng (chờ xác nhận phía khách)</li>
 *     <li>WAIT_FOR_CONFIRMED - Đơn hàng đang chờ được người bán hàng xác nhận</li>
 *     <li>PREPARED - Đơn hàng đang được chuẩn bị giao (phía người bán)</li>
 *     <li>IS_BEING_SHIPPED - Đơn hàng đang được giao</li>
 *     <li>RECEIVED - Đơn hàng đã được nhận</li>
 *     <li>CANCELED - Đơn hàng bị hủy phía khách hàng</li>
 *     <li>FAILED - Đơn hàng không thể hoàn thành do sự cố</li>
 *     <li>IGNORE - Đơn hàng lỗi hoặc từ chối giao hàng, bị bỏ qua</li>
 * </ul>
 * @author Nhat Phi
 * @since 2022-11-18
 * @version 1.1
 * */
public enum OrderStatus {
    PENDING,
    WAIT_FOR_CONFIRMED,
    PREPARED,
    IS_BEING_SHIPPED,
    RECEIVED,
    CANCELED,
    FAILED,
    IGNORE
}
