import SwiftUI
import shared

struct CarouselItemView: View {
    @State var item : MovieItemModel
    
    var width: CGFloat
    var height: CGFloat
    
    var body: some View {
        ZStack(alignment: .bottom) {
            AsyncImage(url: URL(string: item.imageUrl)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFill()
                        .clipped()
                case .failure:
                    Image(systemName: "photo")
                        .resizable()
                        .scaledToFit()
                        .foregroundColor(.gray)
                @unknown default:
                    EmptyView()
                }
            }
            // Gradient Overlay
            LinearGradient(
                gradient: Gradient(colors: [.clear,.clear, .black]),
                startPoint: .top,
                endPoint: .bottom
            )
            
            HStack {
                
                VStack(alignment: .leading) {
                    
                    Text(item.title ?? "")
                        .font(.title2.bold())
                        .foregroundColor(.white)
                        .lineLimit(2) // Restrict to one line
//                        .truncationMode(.tail)
                    
                    HStack(spacing: 4) {
                        
                        Text(formattedVoteAverage(item.vote_average as? Double))
                            .font(.caption)
                            .foregroundColor(.yellow)
                        
                        Image(systemName: "star.fill")
                            .foregroundColor(.yellow)
                            .font(.caption)
                    }
                    
                }
                
                Spacer()
                                
                Button(action: {
                    // Add your action here
                }) {
                    HStack(spacing: 8) {
                        Text("Watch now")
                            .foregroundColor(.white)
                            .font(.headline)
                    }
                    .padding(.horizontal, 28)
                    .padding(.vertical, 12)
                    .background(
                        Color.red.opacity(0.8) // Dark red background with opacity
                            .blur(radius: 16) // Optional blur effect
                            .cornerRadius(24) // Curved edges
                    )
                }
                
                
            }.padding(.horizontal)
            
        }.frame(width: width, height: height)
        
    }
}

//
//#Preview {
//    MyView()
//}
