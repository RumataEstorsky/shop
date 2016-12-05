package models

import play.api.libs.json.Json

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Sales.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Sales
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param shopId Database column shop_id SqlType(int4)
   *  @param saleDate Database column sale_date SqlType(int8)
   *  @param productId Database column product_id SqlType(int4)
   *  @param productCount Database column product_count SqlType(int4), Default(0)
   *  @param price Database column price SqlType(float8)
   *  @param categoryId Database column category_id SqlType(int4)
   *  @param vendorId Database column vendor_id SqlType(int4) */
  case class SalesRow(id: Long, shopId: Int, saleDate: Long, productId: Int, productCount: Int = 0, price: Double, categoryId: Int, vendorId: Int)
  implicit val format = Json.format[SalesRow]
  /** GetResult implicit for fetching SalesRow objects using plain SQL queries */
  implicit def GetResultSalesRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Double]): GR[SalesRow] = GR{
    prs => import prs._
    SalesRow.tupled((<<[Long], <<[Int], <<[Long], <<[Int], <<[Int], <<[Double], <<[Int], <<[Int]))
  }
  /** Table description of table sales. Objects of this class serve as prototypes for rows in queries. */
  class Sales(_tableTag: Tag) extends Table[SalesRow](_tableTag, "sales") {
    def * = (id, shopId, saleDate, productId, productCount, price, categoryId, vendorId) <> (SalesRow.tupled, SalesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(shopId), Rep.Some(saleDate), Rep.Some(productId), Rep.Some(productCount), Rep.Some(price), Rep.Some(categoryId), Rep.Some(vendorId)).shaped.<>({r=>import r._; _1.map(_=> SalesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column shop_id SqlType(int4) */
    val shopId: Rep[Int] = column[Int]("shop_id")
    /** Database column sale_date SqlType(int8) */
    val saleDate: Rep[Long] = column[Long]("sale_date")
    /** Database column product_id SqlType(int4) */
    val productId: Rep[Int] = column[Int]("product_id")
    /** Database column product_count SqlType(int4), Default(0) */
    val productCount: Rep[Int] = column[Int]("product_count", O.Default(0))
    /** Database column price SqlType(float8) */
    val price: Rep[Double] = column[Double]("price")
    /** Database column category_id SqlType(int4) */
    val categoryId: Rep[Int] = column[Int]("category_id")
    /** Database column vendor_id SqlType(int4) */
    val vendorId: Rep[Int] = column[Int]("vendor_id")

    /** Index over (price) (database name idx_sales_price) */
    val index1 = index("idx_sales_price", price)
    /** Index over (productId) (database name idx_sales_product_id) */
    val index2 = index("idx_sales_product_id", productId)
    /** Index over (shopId) (database name idx_sales_shop_id) */
    val index3 = index("idx_sales_shop_id", shopId)
  }
  /** Collection-like TableQuery object for table Sales */
  lazy val Sales = new TableQuery(tag => new Sales(tag))
}
