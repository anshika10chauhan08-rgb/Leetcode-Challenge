class Solution {
public:
    int averageOfSubtree(TreeNode* root) {
        int ans = 0;

        function<pair<int,int>(TreeNode*)> dfs = [&](TreeNode* node) {
            if (node == NULL)
                return pair<int,int>{0, 0};

            auto left = dfs(node->left);
            auto right = dfs(node->right);

            int sum = node->val + left.first + right.first;
            int count = 1 + left.second + right.second;

            if (node->val == sum / count)
                ans++;

            return pair<int,int>{sum, count};
        };

        dfs(root);
        return ans;
    }
};