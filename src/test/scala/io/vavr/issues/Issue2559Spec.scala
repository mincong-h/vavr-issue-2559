package io.vavr.issues

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.Queue
import scala.collection.{StrictOptimizedIterableOps, mutable}

class Issue2559Spec extends FlatSpec with Matchers {

  "Set" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Set(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, Set, Set[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Set(1, 2, 3)
    partition._2 shouldEqual Set()
    count.get() shouldEqual 3
  }

  "List" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = List(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, List, List[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual List(1, 2, 3)
    partition._2 shouldEqual List()
    count.get() shouldEqual 3
  }

  "Seq" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Seq(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, Seq, Seq[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Seq(1, 2, 3)
    partition._2 shouldEqual Seq()
    count.get() shouldEqual 3
  }

  "Queue" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Queue(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, Queue, Queue[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Queue(1, 2, 3)
    partition._2 shouldEqual Queue()
    count.get() shouldEqual 3
  }

  "Map" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Map[String, Int](("1", 1), ("2", 2), ("3", 3))
    // FIXME Enable the test
    // values shouldBe a[StrictOptimizedIterableOps[_, _, _]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Map[String, Int](("1", 1), ("2", 2), ("3", 3))
    partition._2 shouldEqual Map[String, Int]()
    count.get() shouldEqual 3
  }

  "Vector" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Vector(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, Vector, Vector[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Vector(1, 2, 3)
    partition._2 shouldEqual Vector()
    count.get() shouldEqual 3
  }

  "Stack" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = mutable.Stack(1, 2, 3)
    values shouldBe a[StrictOptimizedIterableOps[Int, mutable.Stack, mutable.Stack[Int]]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual mutable.Stack(1, 2, 3)
    partition._2 shouldEqual mutable.Stack()
    count.get() shouldEqual 3
  }

  "Array" should "partition in 1 iteration" in {
    val count = new AtomicInteger(0)

    val values = Array(1, 2, 3)
    // FIXME Enable test
    // values should not be a[StrictOptimizedIterableOps[_, _, _]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Array(1, 2, 3)
    partition._2 shouldEqual Array()
    count.get() shouldEqual 3
  }

  "LazyList" should "partition in 2 iterations" in {
    val count = new AtomicInteger(0)

    val values = LazyList(1, 2, 3)
    // FIXME Enable the test
    // values should not be a[StrictOptimizedIterableOps[_, _, _]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual LazyList(1, 2, 3)
    partition._2 shouldEqual LazyList()
    count.get() shouldEqual 6
  }

  "Stream" should "partition in 2 iterations" in {
    val count = new AtomicInteger(0)

    val values = Stream(1, 2, 3)
    // FIXME Enable the test
    // values should not be a[StrictOptimizedIterableOps[_, _, _]]

    val partition = values.partition(_ => {
      count.incrementAndGet()
      true
    })

    partition._1 shouldEqual Stream(1, 2, 3)
    partition._2 shouldEqual Stream()
    count.get() shouldEqual 6
  }
}
