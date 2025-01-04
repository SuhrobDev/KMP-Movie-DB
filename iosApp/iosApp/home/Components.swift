//
//  Components.swift
//  iosApp
//
//  Created by suhrob on 04/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared


struct PeopleItemView: View {
    let peopleItem: PeopleItemModel
    let onItemClick: (PeopleItemModel) -> Void

    var body: some View {
        Button(action: {
            onItemClick(peopleItem)
        }) {
            VStack {
                AsyncImage(url: URL(string: peopleItem.imageUrl)) { phase in
                    switch phase {
                    case .empty:
                        // Show a placeholder or a loading indicator
                        ProgressView()
                            .frame(width: 60, height: 60)
                            .background(Color.gray.opacity(0.3))
                            .clipShape(RoundedRectangle(cornerRadius: 16))
                    case .success(let image):
                        // Display the loaded image
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: 60, height: 60)
                            .clipShape(RoundedRectangle(cornerRadius: 16))
                    case .failure:
                        // Display a fallback image if loading fails
                        Image(systemName: "person.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 60, height: 60)
                            .foregroundColor(.gray)
                    @unknown default:
                        EmptyView()
                    }
                }
            }
        }
        .buttonStyle(PlainButtonStyle()) // Removes default button styling
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 16))
        .shadow(color: .gray.opacity(0.3), radius: 4, x: 0, y: 2)
    }
}
