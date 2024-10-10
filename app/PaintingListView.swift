import SwiftUI

struct PaintingListView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 20) {
                Text("Lista de Pinturas en Exposición")
                    .font(.largeTitle)
                    .padding()
                // Lista para el conjunto de pinturas
                List {
                    ForEach(0..<10) { index in
                        Text("Pintura \(index + 1) en \(selectedGallery)")
                    }
                }
                Spacer()
            }
        }
        .navigationTitle("Pinturas")
        .padding()
    }
}
