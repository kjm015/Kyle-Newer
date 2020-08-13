package io.github.kjm015.kylenewer.util.computer

data class ComputerCase (
        var name: String,
        var manufacturer: String,
        var formFactor: MotherboardFormFactor,
        var psuFormFactor: PowerSupplyFormFactor,
        var maxRadiatorSupport: Int,
        var maxGPULength: Int,
        var price: Double
)