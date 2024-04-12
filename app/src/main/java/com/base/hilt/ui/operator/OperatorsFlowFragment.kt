package com.base.hilt.ui.operator

import com.base.hilt.R
import com.base.hilt.base.FragmentBase
import com.base.hilt.base.ToolbarModel
import com.base.hilt.databinding.FragmentOperatorsFlowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class OperatorsFlowFragment : FragmentBase<OperatorFlowViewModel, FragmentOperatorsFlowBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_operators_flow

    override fun setupToolbar() {
        viewModel.setToolbarItems(ToolbarModel(false, "", false))
    }

    override fun initializeScreenVariables() {
        runBlocking {
            mapExample()
            filterExample()
            transformExample()
            flatMapLatestExample()
            flatmapConcatExample()
            flatMapMergeExample()
            takeExample()
            dropExample()
            collectExample()
            onEachExample()
            flowOnExample()
            catchExample()
            zipExample()
            conflateExample()
            distinctUtilChanged()
            retryExample()
        }

    }

    /**
     * MAP OPERATOR
     * The map operator is a fundamental operation in Kotlin Flow (and in many other reactive programming libraries) that
    allows you to transform the elements emitted by a Flow into new elements of a potentially different type.
     */
    suspend fun mapExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

        val transformedFlow: Flow<String> = sourceFlow.map { number ->
            "Transformed: $number"
        }
        println("MAP OPERATOR")
        transformedFlow.collect { value ->

            println(value)
        }

    }

    /**
     * FILTER OPERATOR
    The filter operator allows you to selectively include or exclude elements from a Flow based on a given predicate function.
    It operates on each item emitted by the source Flow and includes only the items that satisfy the specified condition.
     */

    suspend fun filterExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

        val filteredFlow: Flow<Int> = sourceFlow.filter { number ->
            number % 2 == 0 // Include only even numbers
        }
        println("FILTER OPERATOR")
        filteredFlow.collect { value ->

            println(value)
        }

    }

    /**
     * TRANSFORM OPERATOR
    The transform operator is a versatile and powerful operator in Kotlin Flow that allows you to apply a more complex transformation to each element emitted by a source Flow.
    It differs from the map operator in that it allows you to emit zero or more elements for each input element and even switch to a different Flow for each input element.
     */

    suspend fun transformExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

        val transformedFlow: Flow<String> = sourceFlow.transform { number ->
            // Simulate some asynchronous processing with a delay
            delay(100)
            emit("Transformed: $number")
            emit("Additional: $number")
        }
        println("TRANSFORM OPERATOR")
        transformedFlow.collect { value ->

            println(value)
        }


    }

    /**
     * FLATMAPCONCATE OPERATOR
    The flatMapConcat operator is an operator in Kotlin Flow that is used for flattening and merging
    multiple Flows (or other asynchronous data sources) into a single Flow, while preserving the order of emissions.
     */

    suspend fun flatmapConcatExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3)

        val transformedFlow: Flow<String> = sourceFlow.flatMapConcat { number ->
            flow {
                emit("Start $number")
                delay(100)
                emit("End $number")
            }
        }
        println("FLATEMAPCONCATE OPERATOR")
        transformedFlow.collect { value ->

            println(value)
        }

    }

    /**
     * FLATMAPMERGE OPERATOR
    The flatMapMerge operator is an operator in Kotlin Flow that is used for flattening and merging multiple Flows
    (or other asynchronous data sources) into a single Flow, without necessarily preserving the order of emissions.
    It applies a transformation function to each item emitted by the source Flow and expects this function to return another Flow.
     */

    suspend fun flatMapMergeExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3)

        val transformedFlow: Flow<String> = sourceFlow.flatMapMerge { number ->
            flow {
                emit("Start $number")
                delay(100)
                emit("End $number")
            }
        }
        println("FLATMAPMERGE OPERATOR")
        transformedFlow.collect { value ->

            println(value)
        }

    }

    /**
     * FLATMAPLATEST OPERATOR
    The flatMapLatest operator is an operator in Kotlin Flow that is used for flattening and merging multiple Flows (or other asynchronous data sources) into a single Flow,
    while ensuring that only the latest emitted item from the source Flow is considered for the transformation.
     */

    suspend fun flatMapLatestExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3)

        val transformedFlow: Flow<String> = sourceFlow.flatMapLatest { number ->
            flow {
                emit("Start $number")
                delay(100)
                emit("End $number")
            }
        }
        println("FLATEMAPLATEST OPERATOR")
        transformedFlow.collect { value ->

            println(value)
        }

    }

    /**
     * TAKE OPERATOR
    The take operator is to limit the number of elements emitted by a Flow.
    It takes an integer parameter specifying the maximum number of items you want to receive from the source Flow.
     */

    suspend fun takeExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

        val takenFlow: Flow<Int> = sourceFlow.take(3)
        println("TAKE OPERATOR")
        takenFlow.collect { value ->

            println(value)
        }

    }

    /**
     * DROP OPERATOR
    The drop operator is an operator in Kotlin Flow that allows you to skip a specified number of elements at the beginning of a Flow and emit the remaining elements.
    It is useful when you want to ignore or "drop" a certain number of items from the start of a Flow before processing the remaining elements
     */

    suspend fun dropExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)

        val droppedFlow: Flow<Int> = sourceFlow.drop(2)
        println("DROP OPERATOR")
        droppedFlow.collect { value ->

            println(value)
        }

    }

    /**
     * COLLECT OPERATOR
    The collect operator is not an operator; rather, it's a terminal operator or a terminal function used to collect and consume elements emitted by a Flow.
    When you apply the collect function to a Flow, you can specify a lambda function to process each emitted element.
     */

    suspend fun collectExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)
        println("COLLECT OPERATOR")
        sourceFlow.collect { value ->
            // Process each element (in this case, print the element)

            println("Received: $value")
        }

    }

    /**
     * ONEACH OPERATOR
    The onEach operator in Kotlin Flow is used to perform a side effect for each element emitted by the Flow while allowing the elements to
    continue flowing through the Flow unchanged. It doesn't modify the elements but enables you to perform some action,
    such as logging, debugging, or monitoring, as the elements pass through the Flow.
     */

    suspend fun onEachExample() {

        val sourceFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)
        println("ONEACH OPERATOR")
        val modifiedFlow: Flow<Int> = sourceFlow
            .onEach { value ->
                // Perform a side effect, such as printing the element

                println("Element: $value")
            }

        modifiedFlow.collect { value ->
            // Process the elements (in this case, just collecting them)

        }
    }

    /**
     * CATCH OPERATOR
    The catch operator is for handling exceptions that might occur during the
    execution of a Flow and allow you to gracefully recover from those exceptions.
     */

    suspend fun catchExample() {
        println("CATCH OPERATOR")
        val sourceFlow: Flow<Int> = flow {
            emit(1)
            emit(2)
            throw RuntimeException("Error occurred!")
            emit(3)
            emit(4)
        }

        val recoveredFlow: Flow<Int> = sourceFlow.catch { exception ->
            // Handle the exception gracefully
            println("Caught exception: ${exception.message}")
            emit(5) // Emit a recovery value
        }

        recoveredFlow.collect { value ->

            println("Received: $value")
        }

    }

    /**
     * FLOWON OPERATOR
    The flowOn operator is used to change the context or thread in which the emissions of a Flow are performed.
    It's often used to switch the execution context of a Flow,
    especially when you want to offload heavy or blocking work to a different thread or dispatcher.
     */

    suspend fun flowOnExample() {
        println("FLOWON OPERATOR")
        val sourceFlow: Flow<Int> = flow {
            for (i in 1..5) {
                println("Emitting $i in thread ${Thread.currentThread().name}")
                emit(i)
                delay(100)
            }
        }

        val transformedFlow: Flow<String> = sourceFlow
            .map { value ->
                "Transformed $value in thread ${Thread.currentThread().name}"
            }
            .flowOn(Dispatchers.IO) // Switch execution context to IO dispatcher

        withContext(Dispatchers.Default) {
            // Collect in a different context (Default dispatcher)
            transformedFlow.collect { value ->

                println("Received: $value in thread ${Thread.currentThread().name}")
            }
        }

    }

    /**
     * ZIP OPERATOR
    The zip operator in Kotlin Flow is used to combine elements from multiple Flows into pairs or tuples.
    It takes two or more Flow instances as arguments and emits elements as pairs,
    where each pair contains one element from each of the input Flows.
     */

    suspend fun zipExample() {
        println("ZIP OPERATOR")
        val numbersFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)
        val lettersFlow: Flow<String> = flowOf("A", "B", "C", "D", "E")

        val zippedFlow: Flow<Pair<Int, String>> =
            numbersFlow.zip(lettersFlow) { number, letter ->
                Pair(number, letter)
            }

        zippedFlow.collect { pair ->

            println("Received: ${pair.first} - ${pair.second}")
        }

    }

    /**
     * CONFLATE OPERATOR
    The conflate operator is used to control the emission of items when the downstream
    (the consumer of the flow) is slower than the upstream (the producer of the flow).
    It is a flow operator that helps in managing backpressure by dropping
    intermediate emitted values when the downstream cannot keep up with the rate of emissions from the upstream.
     */

    suspend fun conflateExample() {
        println("CONFLATEEXAMPLE OPERATOR")
        val flow = flow {
            emit(1)
            delay(100) // Simulate some processing time
            emit(2)
            delay(100) // Simulate some processing time
            emit(3)
        }

        flow
            .conflate() // Use the conflate operator
            .collect { value ->
                delay(200) // Simulate slow processing
                println(value)
            }

    }

    /**
     * DISTINCTUTILCHANGED OPERATOR
    The distinctUntilChanged operator in Kotlin Flow is used to filter out consecutive duplicate elements
    emitted by the source Flow.
    It ensures that only distinct, non-consecutive elements are emitted downstream.
     */

    suspend fun distinctUtilChanged() {
        println("DISTINCTUTILCHANGED OPERATOR")
        val sourceFlow: Flow<Int> = flow {
            emit(1)
            emit(2)
            emit(2) // Duplicate
            emit(3)
            emit(3) // Duplicate
            emit(3) // Duplicate
            emit(4)
        }

        val distinctFlow: Flow<Int> = sourceFlow.distinctUntilChanged()

        distinctFlow.collect { value ->

            println("Received: $value")
        }

    }

    /**
     * RETRY OPERATOR
    The retry operator in Kotlin Flow is used to handle errors by allowing the
    Flow to be resubscribed and retried when an exception occurs during its execution.
    It provides a way to recover from errors and continue processing the Flow.
     */

    suspend fun retryExample() {
        println("RETRY OPERATOR")
        val sourceFlow: Flow<Int> = flow {
            emit(1)
            emit(2)
//                throw RuntimeException("Error occurred!")
            emit(3)
        }

        val retriedFlow: Flow<Int> = sourceFlow.retry(2) // Retry up to 2 times

        retriedFlow.collect { value ->

            println("Received: $value")
        }

    }

    override fun getViewModelClass(): Class<OperatorFlowViewModel> =
        OperatorFlowViewModel::class.java

}