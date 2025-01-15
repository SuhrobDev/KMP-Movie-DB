import SwiftUI
import SwiftfulRouting
import shared

struct ContentView: View {
    @State var greet = "it's custom swiftui"
    
    var body: some View {
        RouterView { anyRouter in
            MyView(router: anyRouter)
        }
//        nanopb
    }
}

struct MyView :View{
    @State private var selectedTab: Int = 0
    
    // Pass reference to Router directly or pull from Environment
    @Environment(\.router) var router2
    let router: AnyRouter
    
    
    
    @MainActor
    var body: some View {
        Section {
            ZStack(alignment: .bottom) {
                // The main content view
                VStack {
                    if selectedTab == 0 {
                        HomeScreen()
                    } else if selectedTab == 1 {
                        SearchScreen()
                    } else {
                        ProfileScreen()
                    }
                }
                
                // Floating Tab Bar
                HStack {
                    Spacer()
                    
                    Button(action: { selectedTab = 0 }) {
                        Image(systemName: "house.fill")
                            .font(.title)
                            .foregroundColor(selectedTab == 0 ? .white.opacity(0.9) : .gray)
                            .frame(width: 48, height: 48)
                            .background(Circle().fill(Color.white.opacity(0)))
                    }
                    
                    Spacer()
                    
                    Button(action: { selectedTab = 1 }) {
                        Image(systemName: "magnifyingglass")
                            .font(.title)
                            .foregroundColor(selectedTab == 1 ? .white.opacity(0.9) : .gray)
                            .frame(width: 48, height: 48)
                            .background(Circle().fill(Color.white.opacity(0)))
                    }
                    
                    Spacer()
                    
                    Button(action: { selectedTab = 2 }) {
                        Image(systemName: "person.fill")
                            .font(.title)
                            .foregroundColor(selectedTab == 2 ? .white.opacity(0.9) : .gray)
                            .frame(width: 48, height: 48)
                            .background(Circle().fill(Color.white.opacity(0)))
                    }
                    
                    Spacer()
                    
                }
                .frame(height: 56)
                .background(
                    LinearGradient(
                        gradient: Gradient(colors: [Color.red, Color.black]),
                        startPoint: .leading,
                        endPoint: .trailing
                    )
                )
                .cornerRadius(24, corners: [.topLeft, .topRight]) // Apply top corners radius 24
                .cornerRadius(50, corners: [.bottomLeft, .bottomRight]) // Apply bottom corners radius 50
                .padding(.horizontal, 30)
                .shadow(radius: 10)
            }
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

