//
//  Formatter.swift
//  iosApp
//
//  Created by suhrob on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import Foundation

func formattedVoteAverage(_ value: Double?) -> String {
    guard let value = value else { return "0" }
    return value.truncatingRemainder(dividingBy: 1) == 0
    ? String(format: "%.0f", value)
    : String(format: "%.1f", value)
}
