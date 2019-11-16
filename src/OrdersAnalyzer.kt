import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer {
    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    /**
     * Calculate the total ordered quantity in [orders] for every weekday.
     * @return a map with a DayOfWeek as a key and the sum of ordered quantity for all products, as a value.
     */
    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        var numOfOrdersPerDayOfWeek: HashMap<DayOfWeek, Int> = HashMap();

        for (order in orders) {
            var dayOfWeek: DayOfWeek = order.creationDate.dayOfWeek;
            for (orderLine in order.orderLines) {
                addOrderQuantityToDay(orderLine, numOfOrdersPerDayOfWeek, dayOfWeek)
            }
        }

        return numOfOrdersPerDayOfWeek.toSortedMap();
    }

    private fun addOrderQuantityToDay(orderLine: OrderLine, numOfOrdersPerDayOfWeek: HashMap<DayOfWeek, Int>, dayOfWeek: DayOfWeek) {
        var quantity = orderLine.quantity;
        if (quantity > 0) {
            if (!numOfOrdersPerDayOfWeek.containsKey(dayOfWeek)) {
                numOfOrdersPerDayOfWeek.put(dayOfWeek, 0);
            }
            var updateOrderSum: Int = numOfOrdersPerDayOfWeek.get(dayOfWeek)!! + quantity;
            numOfOrdersPerDayOfWeek.put(dayOfWeek, updateOrderSum);
        }
    }
}
