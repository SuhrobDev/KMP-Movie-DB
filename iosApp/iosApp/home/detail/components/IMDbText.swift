//
//  IMDbText.swift
//  iosApp
//
//  Created by suhrob on 07/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct IMDbText: View {
    let rating: Double
    var body: some View {
        Text("IMDb " + "\(formattedVoteAverage(rating))")
            .font(.system(size: 20, weight: .bold))
            .foregroundColor(.black) // Black text
            .padding(.horizontal, 8) // Add horizontal padding for the box
            .padding(.vertical, 4) // Add vertical padding for the box
            .background(Color.yellow) // IMDb gold background
            .cornerRadius(4) // Rounded corners
    }
}
